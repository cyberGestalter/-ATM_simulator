package com.javarush.task.task26.task2613;

//Поддерживаемые программой операции
public enum Operation {
    LOGIN, INFO, DEPOSIT, WITHDRAW, EXIT;

    //1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT
    public static Operation getAllowableOperationByOrdinal(Integer i) {
        switch (i) {
            case 1 : return INFO;
            case 2 : return DEPOSIT;
            case 3 : return WITHDRAW;
            case 4 : return EXIT;
            case 0 :
            default: throw new IllegalArgumentException();
        }
    }
}
