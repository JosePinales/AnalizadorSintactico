package jpinales.com.learnit2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

public class Seleccion extends AppCompatActivity {

    // Array of strings...
    ListView simpleList ;
  //  private ArrayAdapter aAdapter;
    String categoryList[] = {"Videos de Tecnologia", "Videos de Política", "Videos Musicales", "Videos de Android Studio", "Videos de Muñequitos", "Videos Educativos"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion);

      //  super.onCreate(savedInstanceState);      setContentView(R.layout.activity_main);
        simpleList = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categoryList);
        simpleList.setAdapter(arrayAdapter);
      //  aAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, countryList);
      //  simpleList.setAdapter(aAdapter);

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long arg3) {
               // view.setSelected(true);
              String este_es = categoryList[position];


                switch(position){
                    case 0:
                        Intent window = new Intent(Seleccion.this, Video.class);
                        startActivity(window);
                        break;

                    case 1:
                        Intent window_politica = new Intent(Seleccion.this, VideosDePolitica.class);
                        startActivity(window_politica);
                        break;

                    case 2:
                        Intent window_musical = new Intent(Seleccion.this, VideosMusicales.class);
                        startActivity(window_musical);
                        break;
                    case 3:
                        Intent window_android = new Intent(Seleccion.this, VideosAndroidStudio.class);
                        startActivity(window_android);
                        break;
                        case 4:
                            Intent window_animado = new Intent(Seleccion.this, VideosAnimados.class);
                            startActivity(window_animado);
                            break;
                    case 5:
                        Intent window_educativo = new Intent(Seleccion.this, VideosEducativos.class);
                        startActivity(window_educativo);
                        break;


                    default:
                        Toast.makeText(Seleccion.this,  "" +este_es, Toast.LENGTH_LONG).show();
                        break;
                }

            }
        });

    }



    public void Seleccionar(View view){
        Intent window = new Intent(Seleccion.this, Video.class);
        startActivity(window);
    }
}
