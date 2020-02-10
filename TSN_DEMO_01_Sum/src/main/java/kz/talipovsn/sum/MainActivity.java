package kz.talipovsn.sum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText_a; // Переменная для доступа к компоненту со значением "a"
    EditText editText_b; // Переменная для доступа к компоненту со значением "b"
    EditText editText_x; // Переменная для доступа к компоненту со значением "x"
    TextView textView_sum; // Переменная для доступа к компоненту со значением результата
    Button buttonSum; // Переменная для доступа к компоненту кнопки

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Доступ к компонентам окна
        editText_a = (EditText) findViewById(R.id.editText_a);
        editText_b = (EditText) findViewById(R.id.editText_b);
        editText_x = (EditText) findViewById(R.id.editText_x);
        textView_sum = (TextView) findViewById(R.id.textView_sum);
        buttonSum = (Button) findViewById(R.id.buttonSum);

        // Проверка на переворот экрана и восстановление нужных значений в компонентах
        if (savedInstanceState != null) {
            textView_sum.setText(savedInstanceState.getString("y"));
        }

        // Собственный обработчик нажатий на клавиши
        View.OnKeyListener myKeyListener = new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // Проверка условия: если пусто в "a" или "b"
                if (editText_a.getText().toString().trim().equals("") ||
                        editText_b.getText().toString().trim().equals("")||
                        editText_x.getText().toString().trim().equals("")) {
                    buttonSum.setEnabled(false); // Выключаем доступность нажатия у кнопки
                } else {
                    buttonSum.setEnabled(true); // Включаем доступность нажатия у кнопки
                }
                return false;
            }
        };

        buttonSum.setEnabled(false); // Выключаем доступность нажатия у кнопки
        editText_a.setOnKeyListener(myKeyListener); // Добавляем к компоненту свой обработчик нажатий
        editText_b.setOnKeyListener(myKeyListener); // Добавляем к компоненту свой обработчик нажатий
        editText_x.setOnKeyListener(myKeyListener); // Добавляем к компоненту свой обработчик нажатий
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Сохранение нужных нам значений компонент при перевороте экрана
        outState.putString("y", textView_sum.getText().toString());
    }

    // МЕТОД КНОПКИ РАСЧЕТА
    public void onClick(View v) {
        // Объявление локальных переменных
        double a, b, x, c;

        try { // НАЧАЛО ЗАЩИЩЕННОГО БЛОКА

            // Чтение данных из компонент
            a = Double.valueOf(editText_a.getText().toString().trim());
            b = Double.valueOf(editText_b.getText().toString().trim());
            x = Double.valueOf(editText_x.getText().toString().trim());

            if (x>=4){
                // Основной алгоритм
                c = ( x + (4 * a) ) / ((a * a) * (b * b));
            } else {
                c = ((x * x * x) - (a * b));
            }


            // Вывод полученного значения в компонент
            textView_sum.setText(String.valueOf(c));

        } catch (Exception e) { // ЧТО ДЕЛАТЬ ЕСЛИ ВОЗНИКНЕТ ОШИБКА В БЛОКЕ МЕЖДУ "TRY" И "CATCH":

            // Вывод сообщения об ошибке
            textView_sum.setText("Неверные входные данные для расчета!");

        }  // КОНЕЦ ЗАЩИЩЕННОГО БЛОКА

    }

}
