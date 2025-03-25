//package com.java.jpa.entity;
//
//import java.io.Serializable;
//import java.time.Instant;
//import java.util.List;
//import java.util.UUID;
//
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.GenericGenerator;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinTable;
//import jakarta.persistence.ManyToMany;
//
//@Entity(name = "Person")
//@Table(name = "person",
//	schema = "app30sch",
//	catalog = "app30cat")
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@ToString
//public class HelloWorld implements Serializable {
//	private static final long serialVersionUID = 1203789973144894290L;
//
//	@Id
//	@GeneratedValue(generator = "person-uuid")
//	@GenericGenerator(name = "person-uuid",
//	strategy = "org.hibernate.id.UUIDGenerator",
//	parameters = {
//	@Parameter(
//	name = "uuid_gen_strategy_class",
//	value = "org.hibernate.id.uuid.CustomVersionFourStrategy"
//	)
//	})
//	private UUID personId;
//
//	@Column(name = "first_name", columnDefinition = "VARCHAR(100)", nullable = false)
//	private String firstName;
//
//	@Column(name = "last_name", columnDefinition = "VARCHAR(100)", nullable = false)
//	private String lastName;
//
//	@Column(name = "email", columnDefinition = "VARCHAR(150)", nullable = false)
//	private String email;
//
//	@Column(name = "time_stamp", nullable = false)
//	@CreationTimestamp
//	private Instant timeStamp;
//
//	@ManyToMany(cascade = CascadeType.ALL,
//			fetch = FetchType.LAZY)
//
//	@JoinTable(name = "person_car",
//		joinColumns = @JoinColumn(name = "person_id",referencedColumnName = "personId"),
//		inverseJoinColumns = @JoinColumn(name = "car_id",referencedColumnName = "carId"),
//		schema = "app30sch",
//		catalog = "app30cat"
//	)
//	private List<Car> cars;
//
//	public void addCar(Car car) {
//	if(cars == null) {
//	cars = new ArrayList<>();
//	}
//	cars.add(car);
//	}
//
//}
