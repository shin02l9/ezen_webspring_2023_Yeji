package example.parking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    public CarEntityRepository carEntityRepository;

    public List<CarDto> doGet(){
        List<CarEntity> carEntities = carEntityRepository.findAll();

        List<CarDto> carDtoList = new ArrayList<>();
        carEntities.forEach( carEntity -> {
            CarDto carDto = CarDto.builder()
                    .cno( carEntity.getCno() )
                    .cnum( carEntity.getCnum() )
                    .ctype( carEntity.getCtype() )
                    .ctime( carEntity.getCtime() )
                    .build();
            carDtoList.add( carDto );
        });
        return carDtoList;
    }

    public boolean doPost( CarDto carDto){
        CarEntity carEntity = CarEntity.builder()
                .cno( carDto.getCno() )
                .cnum( carDto.getCnum() )
                .ctype( carDto.getCtype() )
                .ctime( carDto.getCtime() )
                .build();
        carEntityRepository.save( carEntity );
        return true;
    }

    @Transactional
    public boolean doPut ( CarDto carDto){
        Optional<CarEntity> optional = carEntityRepository.findById( carDto.getCno());
        if( optional.isPresent() ){
            CarEntity updateEntity = optional.get();

            updateEntity.setCnum( carDto.getCnum() );
            updateEntity.setCtype( carDto.getCtype() );
            return true;
        }
        return false;
    }

    public boolean doDelete( int cno ){
        carEntityRepository.deleteById( cno );
        return true;
    }


}
