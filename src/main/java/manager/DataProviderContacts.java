package manager;

import models.Contact;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderContacts {
    @DataProvider
    public Iterator<Object[]> example(){
        List<Object[]> list=new ArrayList<>();

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]>contactSuccess(){
        List<Object[]>list=new ArrayList<>();
        list.add(new Object[]{
                Contact.builder()
                .name("zz")
                .lastName("aa")
                .phone("155555555500")
                .email("zz@mail.ru")
                .address("aa")
                .description("all")
                .build()
        });
        list.add(new Object[]{
                Contact.builder()
                        .name("zzzRequared")
                        .lastName("zz")
                        .phone("6666666666")
                        .email("zzz@mail.ru")
                        .address("zzz")
                        .build()
        });

        return list.iterator();
    }

}
