package com.riskfactors;

import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;


public class FileParser {

    JsonParser parser = new JsonParser();

    public List<User> createUsersFromFile(String filePath) {
        List<User> users = new ArrayList<>();
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();

        for (File userFolder : listOfFiles) {
            if (userFolder.isDirectory()) {
                users.add(parseUser(userFolder));
            }
        }
        return users;
    }

    protected User parseUser(File folder) {
        User user = new User(Integer.parseInt(folder.getName()));
        for (File deviceFolder : folder.listFiles()) {
            if (deviceFolder.isFile()) {
                continue;
            }
            Device device = new Device(deviceFolder.getName());
            user.addDevice(device);
            for (File logFolder : deviceFolder.listFiles()) {
                switch (logFolder.getName()) {
                    case "call_log":
                        parseCallFile(logFolder, device);
                        break;
                    case "contact_list":
                        parseContactFile(logFolder, device);
                        break;
                    case "sms_log":
                        parseMessageFile(logFolder, device);
                        break;
                    default:
                        break;
                }
            }
        }
        return user;
    }


    protected void parseCallFile(File logFolder, Device device) {

        for (File file : logFolder.listFiles()) {
            try {
                JsonArray callList = parser.parse(new FileReader(file)).getAsJsonArray();
                for (JsonElement element : callList) {
                    JsonObject jCall = element.getAsJsonObject();
                    Call call = new Call();
                    call.setItemID(getElement(jCall, "item_id").getAsInt());
                    call.setName(getElement(jCall, "cached_name").getAsString());
                    call.setDateTime(getElement(jCall, "datetime").getAsLong());
                    call.setPhoneNumber(getElement(jCall, "phone_number").getAsString());
                    call.setCallType(getElement(jCall, "call_type").getAsInt());
                    call.setDuration(getElement(jCall, "duration").getAsInt());
                    device.addData(call);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

    }


    protected void parseContactFile(File logFolder, Device device) {

        for (File file : logFolder.listFiles()) {

            try {
                JsonArray contactList = parser.parse(new FileReader(file)).getAsJsonArray();
                for (JsonElement element : contactList) {
                    JsonObject jContact = element.getAsJsonObject();
                    Contact contact = new Contact();
                    contact.setItemID(getElement(jContact, "item_id").getAsInt());
                    contact.setName(getElement(jContact, "display_name").getAsString());
                    contact.setTimesContacted(getElement(jContact, "times_contacted").getAsInt());
                    contact.setLastTimeContacted(getElement(jContact, "last_time_contacted").getAsLong());
                    contact.setPhotoID(getElement(jContact, "photo_id").getAsInt());
                    JsonArray jPhoneNumbers = getElement(jContact, "phone_numbers").getAsJsonArray();
                    jPhoneNumbers = (jPhoneNumbers == null) ? new JsonArray() : jPhoneNumbers;
                    for (JsonElement arrElement : jPhoneNumbers) {
                        JsonObject jPhoneNum = arrElement.getAsJsonObject();
                        contact.addPhoneNumber(getElement(jPhoneNum, "normalized_phone_number").getAsString());
                    }

                    device.addData(contact);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    protected void parseMessageFile(File logFolder, Device device) {

        for (File file : logFolder.listFiles()) {

            try {
                JsonArray messageList = parser.parse(new FileReader(file)).getAsJsonArray();
                for (JsonElement element : messageList) {
                    JsonObject jMessage = element.getAsJsonObject();
                    Message message = new Message();
                    message.setItemID(getElement(jMessage, "item_id").getAsInt());
                    message.setThreadID(getElement(jMessage, "thread_id").getAsInt());
                    message.setSMSAddress(getElement(jMessage, "sms_address").getAsString());
                    message.setContactID(getElement(jMessage, "contact_id").getAsInt());
                    message.setSMSType(getElement(jMessage, "sms_type").getAsInt());
                    message.setDateTime(getElement(jMessage, "date_time").getAsLong());
                    message.setMessageBody(getElement(jMessage, "message_body").getAsString());
                    device.addData(message);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }


    }

    protected JsonElement getElement(JsonObject obj, String key) {
        JsonElement element = obj.get(key);
        if (element == null || element.isJsonNull()) {
            return new DefaultElement();
        } else {
            return element;
        }
    }
}

