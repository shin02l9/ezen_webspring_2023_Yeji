package example.day02.servlet;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TodoService { // 서비스 : 실제 기능들의 처리를 담당
    // 1. 등록 서비스
    public void register( TodoDto todoDto ){
        System.out.println("TodoService.register");
    }

    // 2. 출력 서비스
    public List<TodoDto> getlist(){
        // 1. 스트림 문법을 사용하지 않을때
        /*List<TodoDto> list = new ArrayList<>();
        for( int i = 0; i<10; i++){
            TodoDto todoDto = TodoDto.builder()
                    .tno((long)i)
                    .title("Todo.."+i)
                    .dueDate(LocalDate.now())
                    .build();
            list.add( todoDto );
        }
        return list;*/
        // 2. 스트림 문법을 사용 했을때
        List<TodoDto> todoDtos =
                IntStream.range( 0 , 10 ) // IntStream(시작정수, 끝정수) : 순회자
                // mapToObj : return 값을 반환 해서 배열/리스트에 대입
                .mapToObj( i -> {TodoDto todoDto = TodoDto.builder()
                        .tno((long)i)
                        .title("Todo.."+i)
                        .dueDate(LocalDate.now())
                        .build();
                    return todoDto;
                }).collect(Collectors.toList());
        return todoDtos;

    }
}
