package view.components;

import exceptions.MenuOptionNotFoundException;

/**
 * Esta interfaz permite unificar el comportamiento de las enumeraciones que representan
 * las opciones de distintos menús dentro de la aplicación, obligándolas  a proveer un
 * carácter identificador (llave) y una descripción de lo que la opción significa.
 * <p>
 * Al implementar esta interfaz, los enums permiten realizar búsquedas de sus elementos
 * para evitar duplicar la lógica en cada uno de ellos.
 * </p>
 * @author Brayan Montiel
 * @see Menu
 */
public interface IMenuOption {
    
    /**
     * @return El carácter llave que activa esta opción del menú.
     */
    char getKey();

    /**
     * @return La descripción o mensaje de la opción del menú.
     */
    String getMessage();

    /**
     * Busca y regresa una opción de menú dentro de un Enum específico basándose en su llave.
     * @param <T> - El tipo del Enum que implementa IMenuOption.
     * @param enumClass - La clase del Enum donde se realizará la búsqueda.
     * @param key - El carácter llave buscado.
     * @return - La opción correspondiente a la llave.
     * @throws MenuOptionNotFoundException Si el carácter no coincide con ninguna opción.
     */
    static <T extends Enum<T> & IMenuOption> T fromKey(Class<T> enumClass, char key) 
            throws MenuOptionNotFoundException {
        
        // enumClass.getEnumConstants() equivale al .values() de un enum dinámico
        for (T option : enumClass.getEnumConstants()) {
            if (option.getKey() == key) {
                return option;
            }
        }
        throw new MenuOptionNotFoundException("Ninguna opción contiene la llave '" + key + "'");
    }

}
