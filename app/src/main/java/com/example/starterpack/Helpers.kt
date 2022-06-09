package com.example.starterpack

/**
 * ПОЖАЛУЙСТА, НЕ ИСПОЛЬЗУЙТЕ ОДИН И ТОТ ЖЕ КОД, ОСОБЕННО КОД ИЗ ПРИМЕРА,
 * ТАК-КАК ВЫ САМИ СЕБЯ ПОДСТАВЯЛЕТЕ ПОД БАНЫ
 *
 * @constructor Create empty Helpers
 */
object Helpers {

    /**
     * Эта функция делает из Map строку для GET запроса
     *
     * @param params
     * @return
     */
    @Throws(Exception::class)
    fun mapToQuery(
        params: MutableMap<String, Any>?
    ): String {
        val sb = StringBuilder()

        params?.entries?.iterator()?.let { it: Iterator<*> ->
            while (it.hasNext()) {
                if (sb.isNotEmpty()) {
                    sb.append('&')
                }
                val (key, value) = it.next() as Map.Entry<*, *>
                sb.append(key).append("=").append(value)
            }
        }

        return sb.toString()
    }
}