package roomescape.exception;

public class EmptyNameException extends EmptyDataException{
    public EmptyNameException() {
        super("이름이 입력되지 않았습니다");
    }
}
