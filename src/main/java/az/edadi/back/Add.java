package az.edadi.back;

import az.edadi.back.entity.university.Speciality;
import az.edadi.back.entity.university.University;
import az.edadi.back.repository.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Add {
    @Autowired
    SpecialityRepository specialityRepository;

    @GetMapping("/add/{group}/{uni}")
    String add(@PathVariable Long group,@PathVariable Long uni) throws IOException {
        File file=new File("C:\\Users\\userr\\Desktop\\camaat\\tys\\src\\main\\resources\\static\\specialities.txt");    //creates a new file instance
        FileReader fr=new FileReader(file, StandardCharsets.UTF_8);   //reads the file
        BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
        StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters
        String line;
        List<Speciality> specialityList = new ArrayList<>();
        while((line=br.readLine())!=null)
        {
            University u = new University();
            u.setId(uni);

            int a=0, b=0;
            a = line.indexOf(" Ə ");
            b =line.indexOf(" Q ");
            line= line.substring(0,a+b+3);
            System.out.println(line);
            String words []=line.split(" ");
            Speciality speciality=new Speciality();
            speciality.setUniversity(u);
            speciality.setSpecialityCode(Long.valueOf(words[0]));
            speciality.setName(line.substring(7,a+b+1));
            speciality.setSpecialityGroup(group);
            speciality.setType(words[words.length-1]);
            specialityList.add(speciality);

        }
        fr.close();    //closes the stream and release the resources
        return specialityRepository.saveAll(specialityList).toString();
    }
}
