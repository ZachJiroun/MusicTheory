package cmsc436.com.musictheory.lessons;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cmsc436.com.musictheory.R;

/**
 * Created by zach on 5/3/16.
 */
public class LessonListFragment extends Fragment {

    private String[] mLessons;
    private String[] mUrls;

    private OnLessonSelected mListener;

    public static LessonListFragment newInstance() { return new LessonListFragment(); }

    public LessonListFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnLessonSelected) {
            mListener = (OnLessonSelected) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnLessonSelected.");
        }

        // Get list of lessons and their urls
        final Resources resources = context.getResources();
        mLessons = resources.getStringArray(R.array.lessons);
        mUrls = resources.getStringArray(R.array.urls);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_lesson_list, container, false);

        final Activity activity = getActivity();
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(new LessonAdapter(activity));
        return view;
    }

    class LessonAdapter extends RecyclerView.Adapter<ViewHolder> {
        private LayoutInflater mLayoutInflater;

        public LessonAdapter(Context context) {
            mLayoutInflater = LayoutInflater.from(context);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            return new ViewHolder(mLayoutInflater.inflate(R.layout.recycler_item_lesson, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {
            final String lesson = mLessons[position];
            final String url = mUrls[position];
            viewHolder.setData(lesson);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onLessonSelected(lesson, url);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mLessons.length;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // Views
        private TextView mLessonNameTextView;

        private ViewHolder(View itemView) {
            super(itemView);

            // Get reference to lesson name
            mLessonNameTextView = (TextView) itemView.findViewById(R.id.lesson_name);
        }

        private void setData(String name) {
            mLessonNameTextView.setText(name);
        }
    }

    public interface OnLessonSelected {
        void onLessonSelected(String lesson, String url);
    }
}
