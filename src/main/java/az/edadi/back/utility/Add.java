package az.edadi.back.utility;

import az.edadi.back.repository.SpecialityRepository;
import az.edadi.back.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Add {
    @Autowired
    SpecialityRepository specialityRepository;

    @Autowired
    UniversityRepository universityRepository;
//
//            <url>
//
//      <loc>http://www.example.com/</loc>
//
//      <lastmod>2005-01-01</lastmod>
//
//      <changefreq>monthly</changefreq>
//
//      <priority>0.8</priority>
//
//   </url>


    @GetMapping("/add/{group}/{uni}")
    String add(@PathVariable Long group, @PathVariable Long uni) throws IOException {

//           String start = "<url>";
//            String end = "</url>";
//            String loc = "<loc>http://www.edadi.az/speciality/";
//            String locend ="</loc>";
//
//            List<Speciality> specialityList=specialityRepository.findAll();
//
//            for(Speciality speciality:specialityList){
//                System.out.println(start);
//                System.out.println(loc+speciality.getSpecialityCode()+locend);
//                System.out.println("<lastmod>2021-08-31</lastmod>");
//                System.out.println("<priority>0.8</priority>");
//
//                System.out.println("<changefreq>monthly</changefreq>");
//                System.out.println(end);
//
//
//            }
//        List<University> universityList=universityRepository.findAll();
//
//          loc = "<loc>http://www.edadi.az/university/";
//        System.out.println("***************************");
//        for(University university:universityList){
//            System.out.println(start);
//            System.out.println(loc+university.getAbbr()+locend);
//
//            System.out.println("<changefreq>monthly</changefreq>");
//            System.out.println("<priority>0.9</priority>");
//            System.out.println("<lastmod>2021-08-31</lastmod>");
//            System.out.println(end);
//        }

//        File file=new File("C:\\Users\\userr\\Desktop\\camaat\\tys\\src\\main\\resources\\static\\specialities.txt");    //creates a new file instance
//        FileReader fr=new FileReader(file, StandardCharsets.UTF_8);   //reads the file
//        BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
//        StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters
//        String line;
//        List<Speciality> specialityList = new ArrayList<>();
//        while((line=br.readLine())!=null)
//        {
//            University u = new University();
//            u.setId(uni);
//
//            int a=0, b=0;
//            a = line.indexOf(" Ə ");
//            b =line.indexOf(" Q ");
//            line= line.substring(0,a+b+3);
//            System.out.println(line);
//            String words []=line.split(" ");
//            Speciality speciality=new Speciality();
//            speciality.setUniversity(u);
//            speciality.setSpecialityCode(Long.valueOf(words[0]));
//            speciality.setName(line.substring(7,a+b+1));
//            speciality.setSpecialityGroup(group);
//            speciality.setType(words[words.length-1]);
//            specialityList.add(speciality);
//
//        }
//        fr.close();    //closes the stream and release the resources
//        return specialityRepository.saveAll(specialityList).toString();

        return "ok";

    }
}
