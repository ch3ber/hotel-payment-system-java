package sales.domain.enums;

public enum PaymentPeriods {
  WEEKLY("Semanal"),
  BIWEEKLY("Quincenal"),
  MONTHLY("Mensual");

  private final String period;

  PaymentPeriods(String period) {
    this.period = period;
  }

  public String getPeriod() {
    return period;
  }
}
