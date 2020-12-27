package com.company.carshowroomssimulatorspring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"rating", "carList"},
        allowGetters = false)
public class CarShowroom implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)

    private long id;
    @Column(name = "showroom_name")
    private String showroomName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "showroom_id")
    private List<Vehicle> carList;

    @Column(name = "showroom_capacity")
    private int maxShowroomCapacity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "showroom")
    private List<Rating> rating;

    public CarShowroom(String showroomName, int maxShowroomCapacity) {
        this.showroomName = showroomName;
        this.maxShowroomCapacity = maxShowroomCapacity;
    }


    public CarShowroom() {

    }

    public List<Rating> getRating() {
        return rating;
    }

    public void setRating(List<Rating> rating) {
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setShowroomName(String showroomName) {
        this.showroomName = showroomName;
    }

    public void setCarList(List<Vehicle> carList) {
        this.carList = carList;
    }

    public void setMaxShowroomCapacity(int maxShowroomCapacity) {
        this.maxShowroomCapacity = maxShowroomCapacity;
    }

    public int getMaxShowroomCapacity() {
        return maxShowroomCapacity;
    }

    public List<Vehicle> getCarList() {
        return carList;
    }

    public String getShowroomName() {
        return showroomName;
    }

    public void addProduct(Vehicle product) {
        if (carList.size() < maxShowroomCapacity) {
            if (carList.size() == 0) {
                carList.add(product);
            } else {
                for (Vehicle v : carList) {
                    if (v.compareTo(product) == 0) {
                        v.changeVehicleAmountByOne(true);
                        break;
                    } else {
                        carList.add(product);
                        break;
                    }
                }
            }
        } else
            System.err.println("Exceeded the Showroom Capacity");


    }

    public void getProduct(Vehicle product) {
        for (Vehicle v : carList) {
            if (v.compareTo(product) == 0) {
                if (v.getAmount() > 1) {
                    v.changeVehicleAmountByOne(false);
                    break;
                } else {
                    carList.remove(v);
                    break;
                }
            }
        }
    }

    public void removeProduct(Vehicle product) {
        carList.remove(product);
    }

    public void removeProduct(String carBrand) {
        carList.remove(search(carBrand));
    }

    public Vehicle search(String productName) {
//
//        return Collections.max(carList, new Comparator<Vehicle>() {
//            @Override
//            public int compare(Vehicle v1, Vehicle v2) {
//                return v1.getBrand().compareTo(productName);
//            }
//        });

        for (Vehicle v : carList) {
            if (v.getBrand().contains(productName)) {
                return v;
            }
        }
        return null;
    }

    public Vehicle searchPartial(String partOfProductName) {
        for (Vehicle v : carList) {
            if (v.getBrand().startsWith(partOfProductName)) {
                return v;
            }
        }
        return null;
    }

    public int countByCondition(ItemCondition condition) {
        int numberOfProducts = 0;
        for (Vehicle v : carList) {
            if (v.getCondition().equals(condition)) {
                numberOfProducts++;
            }
        }
        return numberOfProducts;
    }

    public void summary() {
        for (Vehicle v : carList) {
            v.print();
        }
    }

    public List<Vehicle> sortByName() {
        //carList.sort((o1, o2) -> o1.getBrand().compareTo(o2.getBrand()));
        Collections.sort(carList);
//      carList.sort(new Comparator<Vehicle>() {
//            @Override
//            public int compare(Vehicle o1, Vehicle o2) {
//                return o1.getBrand().compareTo(o2.getBrand());
//            }
//        });
        return carList;

    }

    public List<Vehicle> sortByAmount() {
        Collections.sort(carList, new Vehicle());
//        carList.sort(new Comparator<>() {
//            @Override
//            public int compare(Vehicle o1, Vehicle o2) {
//                return Integer.compare(o1.getAmount(), o2.getAmount());
//            }
//        });
//        Collections.reverse(carList);
        return carList;
    }




    public Vehicle max() {
        return Collections.max(carList, Comparator.comparingInt(Vehicle::getAmount));
    }
}
