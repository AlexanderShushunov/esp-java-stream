package esp.model;

public class RiskRank {

    private final int upBound;
    private final String name;

    public RiskRank(int upBound, String name) {
        this.upBound = upBound;
        this.name = name;
    }

    public int getUpBound() {
        return upBound;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RiskRank riskRank = (RiskRank) o;

        if (upBound != riskRank.upBound) return false;
        return name.equals(riskRank.name);
    }

    @Override
    public int hashCode() {
        int result = upBound;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RiskRank{" +
                "upBound=" + upBound +
                ", name='" + name + '\'' +
                '}';
    }
}
