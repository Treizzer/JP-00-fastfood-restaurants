package com.treizer.fastfood_restaurants.presentation.dto.restaurant;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestaurantInsertDto {

    @NotBlank(message = "No debe ser nulo o vacío el nombre")
    @Size(min = 3, max = 20, message = "No debe tener menos de tres caracteres ni más de veinte")
    String name;

    @NotBlank(message = "No debe ser nulo o vacío la dirección")
    @Size(min = 3, max = 20, message = "No debe tener menos de tres caracteres ni más de veinte")
    String address;

    @NotBlank(message = "No debe ser nulo o vacío la ciudad")
    @Size(min = 3, max = 20, message = "No debe tener menos de tres caracteres ni más de veinte")
    String city;

    @NotBlank(message = "No debe ser nulo o vacío el país")
    @Size(min = 3, max = 20, message = "No debe tener menos de tres caracteres ni más de veinte")
    String country;

    // @Size(min = 1, max = 5, message = "No debe tener menos de una clave ni más de
    // cinco")
    // List<String> keys;
    String keys;

    @Digits(integer = 2, fraction = 8, message = "No debe tener más de dos números enteros y no más de ocho decimales")
    Double latitude;

    @Digits(integer = 2, fraction = 8, message = "No debe tener más de dos números enteros y no más de ocho decimales")
    Double longitude;

    @NotNull
    @Min(value = 3, message = "No puede tener menos de tres digitos el código postal")
    @Max(value = 6, message = "No puede tener más de seis digitos el código postal")
    Integer postalCode;

    @NotBlank(message = "No debe ser nulo o vacío")
    @Size(min = 3, max = 20, message = "No debe tener menos de tres caracteres ni más de veinte")
    String province;

    @NotBlank(message = "No debe ser nulo o vacío")
    @Size(min = 5, max = 70, message = "No debe tener menos de cinco caracteres ni más de setenta")
    String website;

}

/*
 * @NotNull: El campo no debe ser null.
 * 
 * @NotEmpty: El campo no debe ser null y su tamaño debe ser mayor que cero.
 * 
 * @NotBlank: El campo no debe ser null y debe contener al menos un carácter que
 * no sea un espacio en blanco.
 * 
 * @Size: Controla el tamaño de cadenas, listas, arreglos, etc.
 * 
 * @Min y @Max: Define los valores mínimo y máximo para números.
 * 
 * @Email: Verifica si el campo tiene un formato de correo electrónico válido.
 * 
 * @Pattern: Define una expresión regular que el campo debe cumplir.
 * 
 * @Digits: Define el limite de dígitos que puedes ingresar en números enteros y
 * decimales.
 * 
 * @CreditCardNumber: Valida números de tarjetas de crédito
 * 
 * @Past: Solo acepta fechas pasadas del día actual (fechas).
 * 
 * @Future Solo acepta fechas futuras del día actual (fechas).
 * 
 * @AssertTrue: Solo acepta que el valor sea un true (Booleans).
 * 
 * @AssertFalse: Solo acepta que el valor sea un false (Booleans).
 */
