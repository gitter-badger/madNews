package org.madnews.utils;

public class View {
    public interface SimplePost {}
    public interface FullPost extends SimplePost {}
    public interface EditablePost extends FullPost{}
    public interface ManyUsers {}
    public interface OneUser extends ManyUsers{}
}