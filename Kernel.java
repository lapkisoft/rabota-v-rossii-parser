import parser.StaxStreamProcessor;
import user.*;

import javax.xml.stream.XMLStreamException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Kernel {
    static FileWriter userWriter;
    static FileWriter companyWriter;
    static FileWriter universityWriter;
    static final Map<String, Organization> companies = new HashMap<>();
    static final Map<String, University> universities = new HashMap<>();

    public Kernel() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        final String path = "C:\\Users\\grine\\Desktop\\resume.xml";
        userWriter = new FileWriter("C:\\Users\\grine\\Desktop\\users.txt");
        companyWriter = new FileWriter("C:\\Users\\grine\\Desktop\\companies.txt");
        universityWriter = new FileWriter("C:\\Users\\grine\\Desktop\\universities.txt");

        try (StaxStreamProcessor processor = new StaxStreamProcessor(Files.newInputStream(Paths.get(path)))) {
            int i = 0;

            while (true) {
                User user = new User(Role.Person);
                List<String> workElements = new ArrayList<>();
                workElements.add("company_name");
                workElements.add("jobTitle");
                workElements.add("dateTo");
                workElements.add("dateFrom");
                String companyName = "";
                String jobTitle = "";

                do {
                    String element = processor.startElement(workElements, "workExperienceList");

                    if (element == null)
                        break;

                    if (element.equals("company_name")) {
                        companyName = processor.getText();
                        companyName = companyName.replaceAll("'","");
                        companyName = companyName.replaceAll("\"","'");

                        if (!companies.containsKey(companyName)) {
                            Organization newOrg = new Organization(companyName);
                            User userOrg = new User(Role.Employer);
                            userOrg.setOrganizationName(companyName);

                            companies.put(companyName, newOrg);
                            companyWriter.write(String.format("%s,", userOrg.toString()));
                        }
                    }
                    else if (element.equals("jobTitle")) {
                        jobTitle = processor.getText();
                        jobTitle = jobTitle.replaceAll("'","");
                        jobTitle = jobTitle.replaceAll("\"","'");
                    }
                    else if (element.equals("dateTo")) {
                        if (companyName.equals(""))
                            continue;

                        String date = processor.getText();
                        Experience exp = new Experience(companies.get(companyName).getId(), date, ExperienceType.Dismissal);
                        exp.setTitle(String.format("Закончил работать в %s", companyName));
                        user.addExperience(exp);
                    }
                    else if (element.equals("dateFrom")) {
                        if (companyName.equals(""))
                            continue;

                        String date = processor.getText();
                        Experience exp = new Experience(companies.get(companyName).getId(), date, ExperienceType.Work);
                        exp.setTitle(String.format("Начал работать в %s на должности %s", companyName, jobTitle));
                        user.addExperience(exp);
                    }
                }
                while (true);

                List<String> educationElements = new ArrayList<>();
                educationElements.add("graduateYear");
                educationElements.add("specialty");
                educationElements.add("legalName");
                int graduateYear = 0;
                String specialty = "null";
                String uniName = "";

                do {
                    String element = processor.startElement(educationElements, "educationList");

                    if (element == null)
                        break;

                    if (element.equals("graduateYear")) {
                        graduateYear = Integer.parseInt(processor.getText());
                    }
                    else if (element.equals("specialty")) {
                        specialty = processor.getText();
                        specialty = specialty.replaceAll("'","");
                        specialty = specialty.replaceAll("\"","'");
                    }
                    else if (element.equals("legalName")) {
                        uniName = processor.getText();
                        uniName = uniName.replaceAll("'","");
                        uniName = uniName.replaceAll("\"","'");

                        if (!universities.containsKey(companyName)) {
                            University university = new University(uniName);
                            User userUni = new User(Role.University);
                            userUni.setUniversityName(uniName);
                            universities.put(uniName, university);
                            universityWriter.write(String.format("%s,", userUni.toString()));
                        }

                        Education education = new Education(universities.get(uniName).getId(), uniName, specialty, graduateYear);
                        user.addEducation(education);
                    }
                }
                while (true);

                List<String> cvElements = new ArrayList<>();
                cvElements.add("experience");
                cvElements.add("birthday");

                do {
                    String element = processor.startElement(cvElements, "cv");

                    if (element == null)
                        break;

                    if (element.equals("experience")) {
                        int workExp = Integer.parseInt(processor.getText());
                        user.setWorkExperience(workExp);
                    }
                    else if (element.equals("birthday")) {
                        int birthyear = Integer.parseInt(processor.getText());
                        user.setWorkExperience(birthyear);
                    }
                }
                while (true);

                if (++i >= 100) {
                    System.out.println("100!");
                }

                userWriter.write(String.format("%s,", user.toString()));
            }
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }

    }
}
