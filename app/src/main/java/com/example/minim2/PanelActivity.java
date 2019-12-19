package com.example.minim2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PanelActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
   private final String TAG = PanelActivity.class.getSimpleName();
   private List<Element> listDatos;
    private ProgressBar progressBar;


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://do.diba.cat/api/dataset/museus/format/json/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);



        swipeRefreshLayout = findViewById(R.id.my_swipe_refresh);


        ApiCall(null);

        // Delete swipe on items
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                            target) {
                        return false;
                    }
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        adapter.remove(viewHolder.getAdapterPosition());
                        Toast.makeText(PanelActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
                    }
                };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }



    private void ApiCall(final SwipeRefreshLayout mySwipeRefreshLayout) {
            ApiElement apiElement = retrofit.create(ApiElement.class);
            Call<Museums> call = apiElement.getComments("1","2");

            call.enqueue(new Callback<Museums>() {
                @Override
                public void onResponse(Call<Museums> call, Response<Museums> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(PanelActivity.this, "Code: "+response.code(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    // set the results to the adapter
                     Museums museums = response.body();

                    listDatos = museums.getElements();
                    adapter = new MyAdapter(listDatos, PanelActivity.this);
                    recyclerView.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);



                    if(mySwipeRefreshLayout!=null) mySwipeRefreshLayout.setRefreshing(false);
                }
                @Override
                public void onFailure(Call<Museums> call, Throwable t) {
                    if(mySwipeRefreshLayout!=null) mySwipeRefreshLayout.setRefreshing(false);
                    progressBar.setVisibility(View.GONE);
                    String msg = "Error in retrofit: "+t.toString();
                    Log.d(TAG,msg);
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                }
            });
        }


}

