import java.util.*;

public class Test {
    public static void main(String[] args) {
        int[] elementos = {1, 2, 3, 4, 5, -5, 6, -6};
        int targetSum = 5;

        List<Integer[]> result = fourNumberSum(elementos, targetSum);

        printElements(result);
    }

    public static void printElements(List<Integer[]> integers) {
        for (Integer[] value : integers) {
            System.out.print("[");
            int i = 0;
            for (Integer valor : value) {
                if (i == 3) {
                    System.out.print(valor);
                } else {
                    System.out.print(valor + ",");
                }
                i++;
            }
            System.out.println("]");
        }
    }

    public static List<Integer[]> fourNumberSum(int[] array, int targetSum) {
        int quantityOfNumberGrupo = 4;
        Set<Integer[]> resultado = new HashSet<>();

        groupNumbers(array, "", quantityOfNumberGrupo, targetSum, resultado);

        return new ArrayList<>(resultado);
    }

    private static Integer sumValues(String values) {
        String[] split = values.split(",");
        int sum = Integer.parseInt(split[0]) +
                Integer.parseInt(split[1]) +
                Integer.parseInt(split[2]) +
                Integer.parseInt(split[3]);
        return sum;
    }

    private static String orderValuesString(String values) {
        String[] split = values.split(",");

        Set<Integer> sumatoriaSet = new TreeSet<>();
        sumatoriaSet.add(Integer.parseInt(split[0]));
        sumatoriaSet.add(Integer.parseInt(split[1]));
        sumatoriaSet.add(Integer.parseInt(split[2]));
        sumatoriaSet.add(Integer.parseInt(split[3]));

        List<Integer> somatoriaList = new ArrayList<>(sumatoriaSet);

        String cadenaAgregar = String.valueOf(somatoriaList.get(0)) + somatoriaList.get(1) + somatoriaList.get(2) + somatoriaList.get(3);

        return cadenaAgregar;
    }

    private static Integer[] orderValuesInteger(String values) {
        String[] split = values.split(",");

        Set<Integer> sumatoriaSet = new TreeSet<>();
        sumatoriaSet.add(Integer.parseInt(split[0]));
        sumatoriaSet.add(Integer.parseInt(split[1]));
        sumatoriaSet.add(Integer.parseInt(split[2]));
        sumatoriaSet.add(Integer.parseInt(split[3]));

        List<Integer> somatoriaList = new ArrayList<>(sumatoriaSet);

        Integer[] sumatoria = {
                somatoriaList.get(0),
                somatoriaList.get(1),
                somatoriaList.get(2),
                somatoriaList.get(3)
        };
        return sumatoria;
    }

    private static void groupNumbers(int[] elements, String currentValue, int quantityOfNumberGrupo, int targetSum, Set<Integer[]> result) {
        if (quantityOfNumberGrupo == 0) {
            int sum = sumValues(currentValue);

            if (sum == targetSum) {
                String stringToAdd = orderValuesString(currentValue);

                boolean exists = result.stream().filter(
                        s -> {
                            String cadena = String.valueOf(s[0]) + String.valueOf(s[1]) + String.valueOf(s[2]) + String.valueOf(s[3]);

                            if (cadena.equals(stringToAdd)) {
                                return Boolean.TRUE;
                            } else {
                                return Boolean.FALSE;
                            }
                        }
                ).findAny().isPresent();

                if (!exists) {
                    Integer[] currentValuesInteger = orderValuesInteger(currentValue);
                    result.add(currentValuesInteger);
                }
            }
        } else {
            for (int element : elements) {
                if (!currentValue.contains(String.valueOf(element))) {
                    groupNumbers(elements, currentValue + element + ",",
                            quantityOfNumberGrupo - 1,
                            targetSum,
                            result);
                }
            }
        }
    }
}
