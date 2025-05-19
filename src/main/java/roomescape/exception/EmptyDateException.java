package roomescape.exception;

public class EmptyDateException extends EmptyDataException{
    public EmptyDateException() {
        super("날짜 정보가 입력되지 않았습니다");
    }
}
