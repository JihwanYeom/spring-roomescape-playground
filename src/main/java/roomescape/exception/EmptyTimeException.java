package roomescape.exception;

public class EmptyTimeException extends EmptyDataException{
    public EmptyTimeException(){
        super("시간 정보가 입력되지 않았습니다");
    }
}
