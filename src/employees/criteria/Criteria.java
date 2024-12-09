package employees.criteria;

public class Criteria {
  private int limit;
  private int offset;

  public Criteria(int limit, int offset) {
    this.limit = limit;
    this.offset = offset;
  }
}
