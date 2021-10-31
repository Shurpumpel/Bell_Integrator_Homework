package pages;

import java.util.HashMap;
import java.util.Map;

public abstract class CurrencyExchangePage {
    Map<String, Double> currencies;

    public abstract String getBankName();

    public Map<String, Double> getBestCurrencies() {
        Map<String,Double> result = new HashMap<>();
        for(Map.Entry<String, Double> entry : currencies.entrySet()){
            if(entry.getKey().contains("Лучший"))
                result.put(entry.getKey(),entry.getValue());
        }
        return result;
    }

    public Map<String, Double> getWorstCurrencies() {
        Map<String,Double> result = new HashMap<>();
        for(Map.Entry<String, Double> entry : currencies.entrySet()){
            if(entry.getKey().contains("Худший"))
                result.put(entry.getKey(),entry.getValue());
        }
        return result;
    }

    public Map<String, Double> getAllCurrencies() {
        return currencies;
    }

    public abstract void preActions();
}
