package cz.muni.fi.PA165.api.dto;

/**
 * Created by PALO on 6.1.14.
 */
public class AccountDto {

    private Long id;
    private String name;
    private String password;
    private Boolean isAdmin;

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountDto)) return false;

        AccountDto accountDto = (AccountDto) o;

        if (!id.equals(accountDto.id)) return false;
        if (!isAdmin.equals(accountDto.isAdmin)) return false;
        if (!name.equals(accountDto.name)) return false;
        if (!password.equals(accountDto.password)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + isAdmin.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
