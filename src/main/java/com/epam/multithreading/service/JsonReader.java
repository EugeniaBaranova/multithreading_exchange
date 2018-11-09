package com.epam.multithreading.service;

import com.epam.multithreading.entity.ExchangeParticipant;
import com.epam.multithreading.service.exeption.ServiceException;
import com.epam.multithreading.utils.FileUtil;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class JsonReader {

    private static final Logger logger = Logger.getLogger(JsonReader.class);

    private FileUtil fileUtil = new FileUtil();

    private List<String> participantNames = new ArrayList<>(Arrays.asList(
            "First Exchange Participant",
            "Second Exchange Participant",
            "Third Exchange Participant"));

    public List<ExchangeParticipant> read(String fileName) throws ServiceException {
        if (fileName != null) {
            Optional<String> filePathOptional = fileUtil.getFilePath(fileName);
            if (filePathOptional.isPresent()) {
                String filePath = filePathOptional.get();
                List<ExchangeParticipant> exchangeParticipants = new ArrayList<>();

                try {

                    JSONParser parser = new JSONParser();
                    FileReader fileReader = new FileReader(filePath);
                    Object obj = parser.parse(fileReader);
                    JSONObject jsonObject = (JSONObject) obj;

                    for(String name: participantNames){
                        JSONArray participantMoney = (JSONArray) jsonObject.get(name);
                        Iterator<String> iterator = participantMoney.iterator();
                        List<BigDecimal> values = new ArrayList<>();
                        while (iterator.hasNext()) {
                            String current = iterator.next();
                            Double currentDouble = Double.valueOf(current);
                            BigDecimal currentValue = BigDecimal.valueOf(currentDouble);
                            values.add(currentValue);
                        }

                    }
                    JSONArray participantMoney = (JSONArray) jsonObject.get("First Exchange Participant");

                    Iterator<String> iterator = participantMoney.iterator();
                    while (iterator.hasNext()) {
                        String next = iterator.next();
                    }

                } catch (IOException | ParseException e) {
                    logger.error(e.getMessage(), e);
                    throw new ServiceException(e);
                }

            }
        }
        return new ArrayList<>(0);
    }


}
