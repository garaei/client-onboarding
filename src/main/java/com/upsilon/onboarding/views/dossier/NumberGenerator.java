package com.upsilon.onboarding.views.dossier;

import java.util.ArrayList;
        import java.util.List;

public class NumberGenerator {

    public static void main(String[] args) {
        NumberGenerator generator = new NumberGenerator();
        int count = 10;
        List<Long> generatedNumbers = generator.generateNumbers(702208, count);

        for (long number : generatedNumbers) {
            System.out.println(number);
        }

        // Generate more numbers as needed
        List<Long> additionalNumbers = generator.generateNumbers(702208, count);

        for (long number : additionalNumbers) {
            System.out.println(number);
        }
    }

    public List<Long> generateNumbers(long initialNumber, int count) {
        List<Long> numbers = new ArrayList<>();
        numbers.add(initialNumber);

        // Define the operations for generating subsequent numbers
        long[] operations = { 274999, -243660, -165835, 394976, -312687, 254506, -475900, 358282, -153013 };

        for (int i = 1; i < count; i++) {
            long nextNumber = numbers.get(i - 1) + operations[i - 1];
            numbers.add(nextNumber);
        }

        return numbers;
    }

    public static void generateNumbers(long initialNumber) {
        if(initialNumber ==0)
            initialNumber = 702208;
        long subtractValue = 72501;

        // Generate and print the first three numbers
        for (int i = 0; i < 3; i++) {
            System.out.println(initialNumber);
            initialNumber -= subtractValue;
        }
    }
}

