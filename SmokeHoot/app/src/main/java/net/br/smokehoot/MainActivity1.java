package net.br.smokehoot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //definir o plano de fundo da barra de status para transparente

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //reciclagem de instalação com o adaptador

        RecyclerView recyclerView =findViewById(R.id.rv_list);
        List<item> mlist = new ArrayList<>();

        mlist.add(new item(R.drawable.minafumando));
        mlist.add(new item(R.drawable.ambiente));
        mlist.add(new item(R.drawable.fuma));
        mlist.add(new item(R.drawable.galeria));
        mlist.add(new item(R.drawable.minafumaca));
        mlist.add(new item(R.drawable.ambiente2));
        mlist.add(new item(R.drawable.povo));
        mlist.add(new item(R.drawable.minafumaca2));

        Adapter adapter = new Adapter(this,mlist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
