package site;

/**
 * Created by admin on 2016/10/3.
 */

public class Province {
    private int id;
    private String ProvinceName;
    private String ProvinceCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return ProvinceName;
    }

    public void setProvinceName(String provinceName) {
        ProvinceName = provinceName;
    }

    public void setProvinceCode(String provinceCode) {
        ProvinceCode = provinceCode;
    }

    public String getProvinceCode() {
        return ProvinceCode;
    }
}
