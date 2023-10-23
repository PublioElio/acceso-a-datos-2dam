package com.acdat.unit2.sax;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class MyHandler extends DefaultHandler {

    private List<User> users = new ArrayList<>();
    private User user;

    private boolean bfn = false;
    private boolean bln = false;
    private boolean boc = false;

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) {

        if ("user".equals(qName)) {

            user = new User();

            int id = Integer.parseInt(attributes.getValue("id"));
            user.setId(id);
        }

        switch (qName) {
            case "firstname" -> bfn = true;
            case "lastname" -> bln = true;
            case "occupation" -> boc = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {

        if (bfn) {
            user.setFirstName(new String(ch, start, length));
            bfn = false;
        }

        if (bln) {
            user.setLastName(new String(ch, start, length));
            bln = false;
        }

        if (boc) {
            user.setOccupation(new String(ch, start, length));
            boc = false;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)  {

        if ("user".equals(qName)) {
            users.add(user);
        }
    }

    public List<User> getUsers() {

        return users;
    }
}
