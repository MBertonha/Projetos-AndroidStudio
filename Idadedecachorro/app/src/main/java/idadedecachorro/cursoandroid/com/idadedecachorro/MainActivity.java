package idadedecachorro.cursoandroid.com.idadedecachorro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText caixaTexto;
    private Button botaoIdade;
    private TextView resultadoIdade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        caixaTexto = (EditText) findViewById(R.id.txt_CaixaTexto);
        botaoIdade = (Button)findViewById(R.id.btn_Idade);
        resultadoIdade = (TextView) findViewById(R.id.txt_resultadoIdade);

        botaoIdade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Recuperar o que a pessoa digito

                String textoDigitado = caixaTexto.getText().toString();
                if(textoDigitado.isEmpty()){
                    //String vazia
                    resultadoIdade.setText("Nenhum numero digitado");
                }else {
                    int valorDigitado = Integer.parseInt(textoDigitado);
                    int resultadoFinal = valorDigitado * 7;

                    resultadoIdade.setText("A idade em anos humanos é: " + resultadoFinal + " anos");
                }
            }
        });

    }
}
