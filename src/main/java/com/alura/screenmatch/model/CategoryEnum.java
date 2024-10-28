package com.alura.screenmatch.model;

public enum CategoryEnum {
    ANIMATION("Animation"),
    COMEDY("Comedy") ,
    DRAMA("Drama"),
    CRIME("Crime"),
    ACTION("Action");
    private String categoryEnum;
    CategoryEnum(String category) {
        this.categoryEnum = category;
    }
    public static CategoryEnum fromString(String text) {
        for(CategoryEnum category : CategoryEnum.values()) {
            if(category.categoryEnum.equalsIgnoreCase(text)) {
            return category;
            }
        }
        throw new IllegalArgumentException("Ninguna categoría fue encontrada: " + text);
    }
}
