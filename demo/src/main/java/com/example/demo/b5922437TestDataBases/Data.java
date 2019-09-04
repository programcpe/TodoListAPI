package com.example.demo.b5922437TestDataBases;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;

@Component
public class Data implements CommandLineRunner {

    private final String FILE_ENCODE = "UTF-8";
    private final String filePath = "src/externaldata/Thailand-UTF8 Demo.txt";

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CarDataRepository carDataRepository;

    @Autowired
    private CarColorRepository carColorRepository;

    @Autowired
    private CarTypeRepository carTypeRepository;


    @Override
    @Transactional
    public void run(String... arg0) throws Exception {
        Status();
        CarData();
        carColor();
        carType();
        Program_Review();

    }

    private void carColor() {
        carColorRepository.saveAndFlush(new CarColor("Red"));
        carColorRepository.saveAndFlush(new CarColor("Black"));
        carColorRepository.saveAndFlush(new CarColor("White"));
        carColorRepository.saveAndFlush(new CarColor("Titanium Flash"));
    }

    private void CarData() {
        CarData carData1 = new CarData();
        carData1.setModel("AMG-BENZ GT-R");
        carData1.setcC("1800");
        carDataRepository.save(carData1);

        CarData carData2 = new CarData();
        carData2.setModel("PORSCHE 911");
        carData2.setcC("1600");
        carDataRepository.save(carData2);
    }

    private void carType() {
        carTypeRepository.saveAndFlush(new CarType("4-door"));
        carTypeRepository.saveAndFlush(new CarType("3-door"));
        carTypeRepository.saveAndFlush(new CarType("2-door"));
    }

    private void Status() {
        Status status1 = new Status();
        status1.setStatus("Todo");
        statusRepository.save(status1);

        Status status2 = new Status();
        status2.setStatus("Done");
        statusRepository.save(status2);
    }

    private void Program_Review() {
        Status status = statusRepository.findByStatusID((long) 2);
        CarData carData =  carDataRepository.findByCarID((long) 2);
        CarColor carColor =  carColorRepository.findByColorID((long) 2);
        CarType carType =  carTypeRepository.findByCarTypeID((long) 3);

        Review review = new Review();
        review.setCarData(carData);
        review.setCarType(carType);
        review.setCarColor(carColor);
        review.setStatus(status);
        review.setComment("เทสระบบ");
        reviewRepository.saveAndFlush(review);

        Status status2 = statusRepository.findByStatusID((long) 1);
        CarData carData2 =  carDataRepository.findByCarID((long) 2);
        CarColor carColor2 =  carColorRepository.findByColorID((long) 1);
        CarType carType2 =  carTypeRepository.findByCarTypeID((long) 3);

        Review review2 = new Review();
        review2.setCarData(carData2);
        review2.setCarType(carType2);
        review2.setCarColor(carColor2);
        review2.setStatus(status2);
        review2.setComment("เทสระบบที่สอง");
        reviewRepository.saveAndFlush(review2);
    }

}



