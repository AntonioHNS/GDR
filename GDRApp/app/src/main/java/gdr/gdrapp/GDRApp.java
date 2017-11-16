package gdr.gdrapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class GDRApp extends AppCompatActivity {
    BluetoothAdapter meuBluetooth = BluetoothAdapter.getDefaultAdapter();
    public static int ENABLE_BLUETOOTH = 1;
    public static int SELECT_PAIRED_DEVICE = 2;
    public static int SELECT_DISCOVERED_DEVICE = 3;
    ImageButton BTstatus;
    ImageButton bateria;
    ImageButton config;
    ImageButton manual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gdrapp);
        configBotoes();
    }

    @Override
    public void onResume(){
        super.onResume();
        if (meuBluetooth.isEnabled()){
            BTstatus.setContentDescription("desligar bluetooth");
            BTstatus.setBackgroundResource(R.drawable.bton);
        } else {
            BTstatus.setContentDescription("ligar bluetooth");
            BTstatus.setBackgroundResource(R.drawable.btoff);
        }
    }


    public void configBotoes(){
        BTstatus = findViewById(R.id.OnOff);
        bateria = findViewById(R.id.bateria);
        config = findViewById(R.id.configuracao);
        manual = findViewById(R.id.manual);

        BTstatus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setBT();
            }
        });
        manual.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(GDRApp.this, Manual.class));
                finish();
            }
        });
        config.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(GDRApp.this, Manual.class));
                finish();
            }
        });

    }

    public void setBT(){
        if (!meuBluetooth.isEnabled()){
            meuBluetooth.enable();
            BTstatus.setContentDescription("ligar bluetooth");
            BTstatus.setBackgroundResource(R.drawable.bton);
            listaDispositivos();
        } else {
            meuBluetooth.disable();
            BTstatus.setContentDescription("desligar bluetooth");
            BTstatus.setBackgroundResource(R.drawable.btoff);
        }
    }

    public void listaDispositivos(){
        /*List<String> listItems = new ArrayList<String>();

        listItems.add("Item1");
        listItems.add("Item2");
        listItems.add("Item3");

        final CharSequence[] charSequenceItems = listItems.toArray(new CharSequence[listItems.size()]);*/
        final CharSequence[] dispositivos = {"1","2","3"};


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Dispositivos encontrados");
        builder.setItems(dispositivos, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int selecionado) {
                Toast.makeText(getApplicationContext(), "Cor Selecionada: " + dispositivos[selecionado], Toast.LENGTH_LONG).show();
            }
        });
        builder.create().show();
    }
}

