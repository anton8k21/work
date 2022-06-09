package com.example.starterpack

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.appsflyer.attribution.AppsFlyerRequestListener
import org.w3c.dom.Text

/**
 * Добро пожаловать в пример работы с аппсом!
 * Тебе нужен код со строки 31 до строки 102
 *
 * Если ты путаешься в примере и тебе все равно ничего не понятно,
 * получи чистый код для встраивания на PasteBin:
 *
 * https://pastebin.com/WCxc8fpL
 * https://pastebin.com/WCxc8fpL
 * https://pastebin.com/WCxc8fpL
 *
 * @constructor Create empty Main activity
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Ключ от AppsFlyer
         */
        val appsFlyerKey = "SihipwKzzzxbpG3LoUhuPn"

        /**
         * Создаем инстанс AppsFlyer
         */
        val appsFlyer = AppsFlyerLib.getInstance()

        appsFlyer.init(appsFlyerKey, object : AppsFlyerConversionListener {

            /**
             * Данные атрибуции успешно пришли!
             * Можем работать с WebView. Вслю логику WebView
             * пишем внеутри функции onConversionDataSuccess
             *
             * @param p0
             */
            override fun onConversionDataSuccess(p0: MutableMap<String, Any>?) {
                /**
                 * В p0 содержиться нужная нам атрибуция.
                 * Атрибуция - это данные о том, откуда пришел пользователь.
                 * С рекламы или просто скачал прилку с плей маркета
                 */

                /**
                 * Мы должны превратить нашу p0 из Map в строку, но не простую,
                 * а строку из get параметров.
                 *
                 * То есть на выходе мы должны получить строку, вида:
                 * key=value&key2=value2&key3=value3
                 *
                 * Ссылки по теме:
                 * 1. shorturl.at/uIKV6
                 * 2. shorturl.at/dftwM
                 *
                 * Гуглить в сторону: "как сделать из Map строку из get query" или "kotlin map to GET query"
                 *
                 * ПОЖАЛУЙСТА, НЕ ИСПОЛЬЗУЙТЕ ОДИН И ТОТ ЖЕ КОД, ОСОБЕННО КОД ИЗ ПРИМЕРА,
                 * ТАК-КАК ВЫ САМИ СЕБЯ ПОДСТАВЯЛЕТЕ ПОД БАНЫ
                 */
                val mapToQuery: String = Helpers.mapToQuery(p0)

                /**
                 * Тут будет ссылка для нашего веб-вью
                 */
                val url = "https://example.com"

                /**
                 * Загружаем в наш веб-вью.
                 * Не забывайте показывать веб-вью внутри runOnUiThread, иначе ваш веб-вью
                 * может просто не показаться
                 */
                runOnUiThread {
                    findViewById<WebView>(R.id.WebView).loadUrl("$url?$mapToQuery")

                    /**
                     * Показываем экран об успешной настройке проекта
                     */
                    findViewById<FrameLayout>(R.id.waiting_layout).visibility = View.GONE
                    findViewById<FrameLayout>(R.id.success_layout).visibility = View.VISIBLE
                }
            }

            /**
             * Остальные фунции отвечают либо за получение ненужных нам данных
             * либо за ошибки
             */
            override fun onConversionDataFail(p0: String?) { }
            override fun onAppOpenAttribution(p0: MutableMap<String, String>?) { }
            override fun onAttributionFailure(p0: String?) { }
        }, this);


        /**
         * В своем приложении лучше всего использовать строку 106,
         * а не код со строк 109 - 165.
         *
         * Этого будет достаточно, строки 109 - 165 служат больше для
         * того, чтобы вы не ебались с поиском ошибок
         */
        // appsFlyer.start(this)

        appsFlyer.start(this, appsFlyerKey, object : AppsFlyerRequestListener {
            /**
             * Все запросы в апсфлайр выполнены успешно!
             *
             * Но обрабатывать нам это не нужно, либо, если хотите углубиться в работу с AppsFlyer,
             * можете поработать с этой функцией, но она не основная
             */
            override fun onSuccess() {}

            /**
             * Произошла ошибка
             *
             * Но обрабатывать нам это не нужно, либо, если хотите углубиться в работу с AppsFlyer,
             * можете поработать с этой функцией, но она не основная
             * @param errorCode Код ошибки, полученный от AppsFlyer
             * @param errorText Текст ошибки
             */
            @SuppressLint("SetTextI18n")
            override fun onError(errorCode: Int, errorText: String) {

                /**
                 * В этом коде мы просто показываем вам в запущенном приложении те ошибки, которые
                 * вы могли допустить и заранее облегчаем вам еблю с ними :)
                 *
                 * ВАМ ЭТОТ КОД КОПИРОВАТЬ НЕ НУЖНО, ОН СДЕЛАН РАДИ ОБЛЕГЧЕНИЯ ВАШЕГО ДЕБАГИНГА,
                 * ПРИ КОПИРОВАНИИ В СВОЙ ПРОЕКТ ПРОГНОРИРУЙТЕ ЕГО. И НЕ ЗАБЫВАЙТЕ ДЕЛАТЬ РЕФАКТОРИНГ
                 */
                runOnUiThread {
                    when (errorCode) {

                        /**
                         * Ошибка 50 - вы неверно указали DEV ключ
                         */
                        50 -> {
                            findViewById<TextView>(R.id.error_text).text = "Вы неверно указали DEV ключ ($appsFlyerKey)"
                        }

                        else -> {
                            findViewById<TextView>(R.id.error_text).text = errorText
                        }
                    }

                    /**
                     * Показываем сообщение об ошибке
                     *
                     * ВАМ ЭТОТ КОД КОПИРОВАТЬ НЕ НУЖНО, ОН СДЕЛАН РАДИ ОБЛЕГЧЕНИЯ ВАШЕГО ДЕБАГИНГА,
                     * ПРИ КОПИРОВАНИИ В СВОЙ ПРОЕКТ ПРОГНОРИРУЙТЕ ЕГО. И НЕ ЗАБЫВАЙТЕ ДЕЛАТЬ РЕФАКТОРИНГ
                     */
                    findViewById<CardView>(R.id.error_card).animate().apply {
                        duration = 500
                        alpha(1f)
                        start()
                    }
                }
            }

        })
    }
}