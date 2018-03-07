package gasolinaoualcool.cursoandroid.com.gasolonaoualcool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText precoAlcool;
    private EditText precoGasolina;
    private Button botaoVerificar;
    private TextView textoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        precoAlcool = (EditText) findViewById(R.id.txt_alcool);
        precoGasolina = (EditText) findViewById(R.id.txt_gasolina);
        botaoVerificar = (Button) findViewById(R.id.btn_verificar);
        textoResultado = (TextView) findViewById(R.id.txt_resposta);

        botaoVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Recuerar valores digitados
                String textoPrecoAlcool = precoAlcool.getText().toString();
                String textoPrecoGasolina = precoGasolina.getText().toString();

                //converter string para numero
                Double valorAlcool = Double.parseDouble(textoPrecoAlcool);
                Double valorGasolina = Double.parseDouble(textoPrecoGasolina);

                //alcool/gasolina = se vale a pena
                Double resultado = valorAlcool/valorGasolina;

                if(resultado >= 0.7){
                    //compensa usar gasolina
                    textoResultado.setText("É melhor utilizar gasolina");
                }else{
                    //compensa usar alcool
                    textoResultado.setText("É melhor utilizar alcool");
                }

            }
        });
    }


}
