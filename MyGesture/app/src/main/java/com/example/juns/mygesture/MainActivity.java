package com.example.juns.mygesture;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ListActivity {
    // Application status when load data success
    private static final int STATUS_SUCCESS = 0;
    // Application status when cancel loading
    private static final int STATUS_CANCELLED = 1;
    // Application status when no external storage
    private static final int STATUS_NO_STORAGE = 2;
    // Application status when fail to do anything
    private static final int STATUS_NOT_LOADED = 3;

    // Menu rename
    private static final int MENU_ID_RENAME = 1;
    // Menu remove gesture
    private static final int MENU_ID_REMOVE = 2;

    // Request new gesture
    private static final int REQUEST_NEW_GESTURE = 1;

    // Store file
    private final File mStoreFile = new File(Environment.getExternalStorageDirectory(), "gestures");

    // Compare and sort gesture list by Name
    private final Comparator<NamedGesture> mSorter = new Comparator<NamedGesture>() {
        @Override
        public int compare(NamedGesture lhs, NamedGesture rhs) {
            return lhs.name.compareTo(rhs.name);
        }
    };

    // Store of gesture, provide by android
    private static GestureLibrary sStore;

    private GesturesAdapter mAdapter;
    private GesturesLoadTask mTask;
    private TextView txtEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set adapter for listview
        mAdapter = new GesturesAdapter(this);
        setListAdapter(mAdapter);

        // Declare store file
        if (sStore == null) {
            sStore = GestureLibraries.fromFile(mStoreFile);
        }

        // Declare textview Empty
        txtEmpty = (TextView) findViewById(R.id.txtEmpty);

        // Load gesture from store file
        loadGestures();

        // Registers a context menu to be shown for the listview.
        // This method will set the listView.onCreateContextMenuListener on th
        // view to this activity,
        // so onCreateContextMenu(ContextMenu, View, ContextMenuInfo) will be
        // called when it is time to show the context menu.
        registerForContextMenu(getListView());
    }

    // Function to get instance of GestureLibraries store
    static GestureLibrary getsStore() {
        return sStore;
    }

    // Function call to remove all festure by click in Clear button
    public void clear(View v) {
        // Remove gesture by name and gesture
        ArrayList<NamedGesture> arDelete = new ArrayList<NamedGesture>();
        if (mAdapter.getCount() > 0) {
            for (int i=0; i<mAdapter.getCount(); i++) {
                if (mAdapter.getItem(i).checked) {
                    arDelete.add(mAdapter.getItem(i));
                }
            }
        }

        for (int i=0; i<arDelete.size(); i++) {
            deleteGesture(arDelete.get(i));
        }
    }

    /**
     * Function call to create new gesture
     */
    public void addGesture(View v) {
        Intent intent = new Intent(this, CreateActivity.class);
        startActivityForResult(intent, REQUEST_NEW_GESTURE);
    }

    /**
     * @see android .app.Activity#onActivityResult(int, int, android.content.Intent)
     *
     * Load list gesture after create new gesture
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_NEW_GESTURE:
                    loadGestures();
                    break;
            }
        }
    }

    /**
     * Call GesturesLoadTask to load gesture from external storage
     */
    private void loadGestures() {
        if (mTask != null && mTask.getStatus() != GesturesLoadTask.Status.FINISHED) {
            mTask.cancel(true);
        }
        mTask = (GesturesLoadTask) new GesturesLoadTask().execute();
    }

    /**
     * @see android.app.ListActivity#onDestroy()
     *
     * Cancel GesturesLoadTask if it's running Cancle rename dialog if it's
     * showing
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mTask != null && mTask.getStatus() != GesturesLoadTask.Status.FINISHED) {
            mTask.cancel(true);
            mTask = null;
        }
    }

    /**
     * If list gesture is null, show textview "No gestures"
     */
    private void checkForEmpty() {
        if (mAdapter.getCount() == 0) {
            txtEmpty.setVisibility(View.VISIBLE);
            txtEmpty.setText(R.string.gestures_empty);
        }else {
            txtEmpty.setVisibility(View.GONE);
        }
    }

    /**
     * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
     *
     * Register event when click in list item
     */
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        createDialogGesture((NamedGesture) l.getItemAtPosition(position));
    }

    /**
     * @see android.app.Activity#onCreateContextMenu(android.view.ContextMenu, android.view.View, android.view.ContextMenu.ContextMenuInfo)
     *
     * Create context menu when long click in list item with two function: -
     * Rename - Delete
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(((TextView) info.targetView).getText());

        menu.add(0, MENU_ID_RENAME, 0, R.string.gestures_rename);
        menu.add(0, MENU_ID_REMOVE, 0, R.string.gestures_delete);
    }

    /**
     * Delete gesture in external storage
     */
    private void deleteGesture(NamedGesture gesture) {
        // Remove gesture by name and gesture
        sStore.removeGesture(gesture.name, gesture.gesture);
        // Save GestureLibrary
        sStore.save();

        // Reload list gesture
        final GesturesAdapter adapter = mAdapter;
        adapter.setNotifyOnChange(false);
        adapter.remove(gesture);
        adapter.sort(mSorter);
        checkForEmpty();
        adapter.notifyDataSetChanged();
    }

    /**
     * Class use to get gesture from external storage and show to listview
     */
    private class GesturesLoadTask extends AsyncTask<Void, NamedGesture, Integer> {
        private int mThumbnailSize;
        private int mThumbnailInset;
        private int mPatchColor;

        /**
         * @see android.os.AsyncTask#onPreExecute()
         *
         * Refresh listview
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            final Resources resources = getResources();
            mPatchColor = resources.getColor(R.color.gesture_color);
            mThumbnailInset = (int) resources.getDimension(R.dimen.gesture_thumbnail_inset);
            mThumbnailSize = (int) resources.getDimension(R.dimen.gesture_thumbnail_size);

            findViewById(R.id.btnAdd).setEnabled(false);
            findViewById(R.id.btnDelete).setEnabled(false);

            mAdapter.setNotifyOnChange(false);
            mAdapter.clear();
        }

        /**
         * @see android.os.AsyncTask#doInBackground(Object[])
         *
         * Read data from external storage
         */
        @Override
        protected Integer doInBackground(Void... params) {
            if (isCancelled()) {
                return STATUS_CANCELLED;
            }

            if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                return STATUS_NO_STORAGE;
            }

            final GestureLibrary store = sStore;
            if (store.load()) {
                for (String name : store.getGestureEntries()) {
                    if (isCancelled()) {
                        break;
                    }

                    for (Gesture gesture : store.getGestures(name)) {
                        final Bitmap bitmap = gesture.toBitmap(mThumbnailSize, mThumbnailSize, mThumbnailInset, mPatchColor);
                        final NamedGesture namedGesture = new NamedGesture();
                        namedGesture.gesture = gesture;
                        namedGesture.name = name;

                        mAdapter.addBitmap(namedGesture.gesture.getID(), bitmap);
                        publishProgress(namedGesture);
                    }
                }
                return STATUS_SUCCESS;
            }
            return STATUS_NOT_LOADED;
        }

        /**
         * @see android.os.AsyncTask#onProgressUpdate(Object[])
         */
        @Override
        protected void onProgressUpdate(NamedGesture... values) {
            super.onProgressUpdate(values);

            final GesturesAdapter adapter = mAdapter;
            adapter.setNotifyOnChange(false);

            for (NamedGesture gesture : values) {
                adapter.add(gesture);
            }

            adapter.sort(mSorter);
            adapter.notifyDataSetChanged();
        }

        /**
         * @see android.os.AsyncTask#onPostExecute(Object)
         */
        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);

            if (result == STATUS_NO_STORAGE) {
                getListView().setVisibility(View.GONE);
                txtEmpty.setVisibility(View.VISIBLE);
                txtEmpty.setText(getString(R.string.gestures_error_loading, mStoreFile.getAbsolutePath()));
            }else {
                findViewById(R.id.btnAdd).setEnabled(true);
                findViewById(R.id.btnDelete).setEnabled(true);
                checkForEmpty();
            }
        }
    }

    static class NamedGesture {
        String name;
        Gesture gesture;
        boolean checked;
    }

    /**
     * Adapter to control listview data
     */
    private class GesturesAdapter extends ArrayAdapter<NamedGesture> {
        private final LayoutInflater mInflater;
        private final Map<Long, Drawable> mThumbnails = Collections.synchronizedMap(new HashMap<Long, Drawable>());

        public GesturesAdapter(Context context) {
            super(context, 0);
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        void addBitmap(Long id, Bitmap bitmap) {
            mThumbnails.put(id, new BitmapDrawable(bitmap));
        }

        /**
         * Add on item to listview
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_gesture, parent, false);
            }

            final NamedGesture gesture = getItem(position);
            String[] name = gesture.name.split("//");

            final View imgThumnail = (View)convertView.findViewById(R.id.imgThumnail);
            imgThumnail.setBackgroundDrawable(mThumbnails.get(gesture.gesture.getID()));

            final TextView txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            txtTitle.setTag(gesture);
            txtTitle.setText(name[0]);

            final TextView txtTime = (TextView) convertView.findViewById(R.id.txtTime);
            if (name.length == 2) {
                txtTime.setText(name[1]);
            }

            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.cbDelete);
            if (gesture.checked) {
                checkBox.setChecked(true);
            }else {
                checkBox.setChecked(false);
            }
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        gesture.checked = true;
                    }else {
                        gesture.checked = false;
                    }
                }
            });

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createDialogGesture(gesture);
                }
            });
            return convertView;
        }
    }

    /**
     * Show gesture's detail dialog
     */
    private void createDialogGesture(NamedGesture gesture) {
        final Dialog dialog = new Dialog(this, R.style.myBackgroundStyle);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_gesture);

        final Resources resources = getResources();
        int mThumbnailSize = 500;
        int mThumbnailInset = (int) resources.getDimension(R.dimen.gesture_thumbnail_inset);
        int mPatchColor = resources.getColor(R.color.gesture_color);

        ImageView imgGesture = (ImageView) dialog.findViewById(R.id.imgGesture);
        Bitmap b = Bitmap.createBitmap(gesture.gesture.toBitmap(mThumbnailSize, mThumbnailSize, mThumbnailInset, mPatchColor));
        imgGesture.setImageBitmap(b);

        Button btn_OK = (Button) dialog.findViewById(R.id.btn_OK);
        btn_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
        dialog.show();
    }
}
