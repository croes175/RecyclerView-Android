package com.androiddesdecero.reciclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Peso> pesos;
    private RecyclerView listaPesos;
    private PesoAdaptador adaptador;

/*travieso 1*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaPesos=(RecyclerView)findViewById(R.id.rvLista);
        LinearLayoutManager lim = new LinearLayoutManager(this);
        lim.setOrientation(LinearLayoutManager.VERTICAL);
        listaPesos.setLayoutManager(lim);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(listaPesos);
        data();
        inicializaAdaptador();
    }

    private void data() {
        pesos = new ArrayList<>();
        pesos.add(new Peso("juan","0"));
        pesos.add(new Peso("carlos","1"));
        pesos.add(new Peso("maria","2"));
        pesos.add(new Peso("david","3"));
        pesos.add(new Peso("ana","4"));
        pesos.add(new Peso("diego","5"));
        pesos.add(new Peso("jona","6"));
        pesos.add(new Peso("travieso","7"));
        pesos.add(new Peso("barrios","8"));
        pesos.add(new Peso("alvaro","9"));
        pesos.add(new Peso("mendez","10"));

    }

    private void inicializaAdaptador() {
        adaptador = new PesoAdaptador(pesos);
        listaPesos.setAdapter(adaptador);
    }
    private ItemTouchHelper.Callback createHelperCallback(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT){

                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        moveItem(viewHolder.getAdapterPosition(),target.getAdapterPosition(),recyclerView);
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        deleteItem(viewHolder.getAdapterPosition());
                    }
                };
        return simpleItemTouchCallback;
    }

    private void moveItem(int oldPos, int newPos,RecyclerView  v){
        Peso item = (Peso) pesos.get(oldPos);
        item.setmPeso(Integer.toString(newPos));
        Toast.makeText(this,"posicion"+Integer.toString(newPos),Toast.LENGTH_SHORT).show();
        pesos.remove(oldPos);

        pesos.add(newPos, item);

        adaptador.notifyItemMoved(oldPos, newPos);
        View v1=(View)v;
        listaPesos.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {

                    adaptador = new PesoAdaptador(pesos);
                    listaPesos.setAdapter(adaptador);
                }
                return false;
            }
        });


    }

    private void deleteItem(final int position){
        pesos.remove(position);

        adaptador.notifyItemRemoved(position);
        adaptador = new PesoAdaptador(pesos);
        listaPesos.setAdapter(adaptador);

    }
}
