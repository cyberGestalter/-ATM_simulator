package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

//хранит всю информацию про выбранную валюту
public class CurrencyManipulator {
    //трехбуквенный код валюты (напр., USD)
    private String currencyCode;
    //Map<номинал, количество>
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    //добавляет введенные номинал и количество банкнот
    public void addAmount(int denomination, int count) {
        if (!denominations.containsKey(denomination)) {
            denominations.put(denomination, count);
        } else {
            int newCount = denominations.get(denomination) + count;
            denominations.put(denomination, newCount);
        }
    }

    //считает общую сумму денег для выбранной валюты
    public int getTotalAmount() {
        int result = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            result += entry.getKey()*entry.getValue();
        }
        return result;
    }

    public boolean hasMoney() {
        return getTotalAmount() > 0;
    }

    //Определяет, достаточно ли денег для выдачи
    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    //Возвращает минимальное количество банкнот, которыми набирается запрашиваемая сумма
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        SortedMap<Integer, Integer> list = new TreeMap(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        list.putAll(denominations);

        //HashMap<Integer, Integer> resultMap = new HashMap<>();
        TreeMap<Integer, Integer> resultMap = new TreeMap<>(Comparator.reverseOrder());
        int summNabrTmp = 0;//пробуем не будет ли сумма больше, в процессе набора
        int summNabr = 0; //набранная сумма
        int expAmountTmp = expectedAmount;
        boolean otdano = false;
        for(Map.Entry<Integer, Integer> entry:list.entrySet()){//обходим мапу и жадным методом набираем нужную сумму
            Integer nominal = entry.getKey();
            for(int i=0;i<entry.getValue();i++){
                summNabrTmp=summNabrTmp+nominal;
                if(summNabrTmp==expAmountTmp){
                    resultMap.put(nominal,i+1);
                    for(Map.Entry<Integer, Integer> entry2:resultMap.entrySet()){
                        Integer collCup = denominations.get(entry2.getKey())-entry2.getValue();
                        if(collCup!=0) {
                            denominations.put(entry2.getKey(), collCup);
                        }else if(collCup==0){
                            denominations.remove(entry2.getKey());
                        }
                    }
                    return resultMap;
                }else if(summNabrTmp>expAmountTmp){
                    if(i!=0) {
                        summNabrTmp = summNabrTmp - nominal;
                        resultMap.put(nominal, i);
                        otdano = true;
                    }else {
                        summNabrTmp = summNabrTmp - nominal;
                        otdano = true;
                    }
                    break;
                }

            }
            if(!otdano)
                resultMap.put(nominal,entry.getValue());
            otdano=false;
        }

        if(summNabr!=expectedAmount){

            throw new NotEnoughMoneyException();
        }

        for(Map.Entry<Integer, Integer> entry:resultMap.entrySet()){
            Integer collCup = denominations.get(entry.getKey())-entry.getValue();
            if(collCup!=0) {
                denominations.put(entry.getKey(), collCup);
            }else if(collCup==0){
                denominations.remove(entry.getKey());
            }
        }
        return resultMap;
    }
}