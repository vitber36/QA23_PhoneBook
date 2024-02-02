package manager;

import models.Contact;
import org.testng.annotations.DataProvider;

import java.io.*;
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

    @DataProvider
    public Iterator<Object[]> contactWrongPhone(){
        List<Object[]> list=new ArrayList<>();
        list.add(new Object[]{
                Contact.builder()
                        .name("www")
                        .lastName("www")
                        .phone("123")
                        .email("www@mail.ru")
                        .address("www")
                        .description("www")
                        .build()
        });
        list.add(new Object[]{
                Contact.builder()
                        .name("www")
                        .lastName("www")
                        .phone("12321213412342342")
                        .email("www@mail.ru")
                        .address("www")
                        .description("www")
                        .build()
        });
        list.add(new Object[]{
                Contact.builder()
                        .name("www")
                        .lastName("www")
                        .phone("wwwwwwwwwwww")
                        .email("www@mail.ru")
                        .address("www")
                        .description("www")
                        .build()
        });
        list.add(new Object[]{
                Contact.builder()
                        .name("www")
                        .lastName("www")
                        .phone("")
                        .email("www@mail.ru")
                        .address("www")
                        .description("www")
                        .build()
        });

        return list.iterator();
    }


        @DataProvider
        public Iterator<Object[]> contactCSV() throws IOException {
            List<Object[]> list=new ArrayList<>();
            BufferedReader reader=new BufferedReader(new FileReader(new File("src/test/resources/contact")));
            String line=reader.readLine();
            while (line!=null){
                String[] all=line.split(",");
                list.add(new Object[]{
                        Contact.builder()
                                .name(all[0])
                                .lastName(all[1])
                                .email(all[2])
                                .phone(all[3])
                                .address(all[4])
                                .description(all[5])
                                .build()
                });
                line=reader.readLine();
            }
            return list.iterator();
        }

}
