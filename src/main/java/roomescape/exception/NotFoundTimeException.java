package roomescape.exception;

public class NotFoundTimeException extends RuntimeException {
  public NotFoundTimeException(Long id) {
    super("ID : " + id + " 에 해당하는 시간이 존재하지 않습니다.");
  }
}
