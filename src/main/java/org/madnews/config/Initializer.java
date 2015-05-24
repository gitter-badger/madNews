package org.madnews.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
/**
 * Инициализирует приложение. Считывает настройки из классов RootConfig, WebAppConfig и регистрирует маппинг для сервлетов(jsp файлов тоже)
 */
public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Метод запрашивает root настройки. В основном, там всегда хранятся все настройки для связи с базой данных.
     * Возвращает массив, потому что классов с настройками может быть несколько.
     * @return массив типа Class, который содержит наш RootConfig.class
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {RootConfig.class};
    }

    /**
     * Метод запрашивает настройки конфигурации. Там у нас конвертеры для работы с json,
     * метод для работы с ресурсами. По простому - ссылки, которые контроллер будет игнорировать(не будет маппить). пути к изображениям, например).
     * Возвращает массив, потому что классов с настройками может быть несколько.
     * @return массив типа Class, который содержит наш WebAppConfig.class
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {WebAppConfig.class};
    }

    /**
     * Метод запрашивает паттерн ссылки, которую будет обрабатывать. Например, "/" подходит под любую ссылку. Любая ссылка содержит слеш.
     * Бывает, например, "*.form" это значит обрабатывать все ссылки, которые заканчиваются на ".form".
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
