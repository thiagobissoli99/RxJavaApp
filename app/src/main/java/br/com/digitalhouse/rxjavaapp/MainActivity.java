package br.com.digitalhouse.rxjavaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.Arrays;

import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity {

    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(view -> {
            comecandoCOmRxJava();
        });
        comecandoCOmRxJava();
    }

    public void comecandoCOmRxJava() {

        Observable.range(1, 100)
                .map(integer -> integer * 3)
                .filter(integer -> integer % 2 == 0)
                .first(10)
                .subscribe(numero -> {
                    System.out.println(numero);
                });

        Observable.just(10)
                .subscribe(System.out::println);

        Observable.fromIterable(Arrays.asList("Mateus", "Person","Nika","Carolina"))
                .filter(s -> s.contains("M"))
                .subscribe(System.out::println);

        Observable<String> stringObservable = Observable.create(emitter -> {

            try{
                emitter.onNext("Mateus");
                emitter.onNext("Nika");

                emitter.onError(new Exception("Erro não manipulavel"));

                emitter.onNext("Fred");
                emitter.onComplete();
            }catch (Exception e){
                emitter.onError(e);
            }

        });

        stringObservable.subscribe(s -> {
            System.out.println(s);
        }, throwable -> {
            System.out.println(throwable.getMessage());
        }, () -> {
            System.out.println("Completou!");
        });
    }


}
