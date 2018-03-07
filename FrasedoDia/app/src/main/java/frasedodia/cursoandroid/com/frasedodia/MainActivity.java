package frasedodia.cursoandroid.com.frasedodia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int contator[];
    private TextView textoNovaFrase;
    private Button botaoNovaFrase;
    private String[] frases = {"Curta todos os instantes de hoje com alegria no coração e paz na alma. Tenha um dia muito especial!",
            "Bom dia com amor para todos vocês que tanta alegria e cor dão à minha vida!",
            "Tenha um bom dia e desfrute de cada instante com otimismo e muita alegria!",
            "Tenha um bom dia abençoado e alegre do princípio ao fim!",
            "Hoje agradeça pela vida e viva-a sem medos. Bom dia!",
            "Ao amanhecer não abra apenas os olhos, mas o coração para as bênçãos que este dia vai trazer!",
            "Confie em si mesmo para conquistar tudo que falta. Bom dia!",
            "Um ótimo dia para você que acredita que um sorriso fica sempre bem!",
            "Sonhos podem acontecer quando menos esperamos. Bom dia!",
            "Hoje levante-se, agarre a vida com energia e tenha um dia feliz!",
            "A felicidade se constrói dando o máximo em cada instante da vida. Bom dia!",
            "Bom dia! Que cada instante de hoje seja especial e inesquecível.",
            "Distribua sua energia positiva pelo mundo e pelas pessoas e sinta-se feliz. Bom dia!",
            "Bom dia! Sinto que coisas maravilhosas estão vindo hoje na sua direção.",
            "Hoje espalhe positividade e alegria por onde passar. Bom dia!",
            "Que hoje a alegria seja o caminho e a paz a maior companhia. Bom dia!",
            "Bom dia, sempre com boas energias e passado na melhor companhia!",
            "Apenas o agora importa, por isso este é o momento para lutar pela sua felicidade. Bom dia!",
    "Deixe as dores e as incertezas no passado, pois o melhor ainda está para acontecer. Bom dia!",
    "Que hoje haja mais sorrisos que lágrimas e sonhos maiores que as derrotas. Bom dia!",
    "O melhor momento para vencer é o agora. Tenha um ótimo dia!",
    "Que sorrir seja sempre uma prioridade na sua vida. Bom dia!",
    "Sorria, hoje vai ser o melhor dia da sua vida!",
    "Que a cada hora que passa a felicidade cresça em nossos corações. Bom dia!",
    "Que esta manhã seja o começo do melhor dia das nossas vidas!",
    "Que a felicidade seja a sua grande conquista de hoje. Bom dia!"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoNovaFrase = (TextView) findViewById(R.id.txt_Frase);
        botaoNovaFrase = (Button) findViewById(R.id.btn_NovaFrase);
        textoNovaFrase.setText("Nenhuma Frase!");

        botaoNovaFrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random randomico = new Random();
                int numeroRandomico = randomico.nextInt(frases.length);

                textoNovaFrase.setText(frases[numeroRandomico]);
            }
        });



    }
}
