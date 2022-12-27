package com.pharmacy.demo.models.dao;

import com.pharmacy.demo.exceptions.BadRequestException;
import com.pharmacy.demo.exceptions.NotFoundException;
import com.pharmacy.demo.models.dto.medicineDTO.FilterMedicineDTO;
import com.pharmacy.demo.models.pojo.*;
import com.pharmacy.demo.models.repository.SaleRepository;
import com.pharmacy.demo.models.repository.ShelfRepository;
import com.pharmacy.demo.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MedicineDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ShelfRepository shelfRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SaleRepository saleRepository;

    public List<Medicine> filterMedicine(int userId, FilterMedicineDTO dto) {
        List<Medicine> medicines = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM medicines WHERE pharmacy_id = ? ");
        boolean nameIncludedInFilter = false;
        boolean priceIncluded = false;
        boolean strengthIncluded = false;
        boolean manufacturerIncluded = false;
        boolean expiryDateIncluded = false;
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        if (dto.getName() != null) {
            sql.append("AND name LIKE ? ");
            nameIncludedInFilter = true;
        }
        if (dto.getPrice() != null) {
            sql.append("AND price = ? ");
            priceIncluded = true;
        }
        if (dto.getManufacturer() != null) {
            sql.append("AND manufacturer LIKE ? ");
            manufacturerIncluded = true;
        }
        if (dto.getStrength() != null) {
            sql.append("AND strength LIKE ? ");
            strengthIncluded = true;
        }
        if (dto.getExpiryDate() != null) {
            sql.append("AND expiry_date = ?");
            expiryDateIncluded = true;
        }
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            int paramIdx = 1;
            PreparedStatement ps = connection.prepareStatement(sql.toString());
            ps.setInt(paramIdx++, optionalUser.get().getPharmacy().getId());
            if (nameIncludedInFilter) {
                ps.setString(paramIdx++, dto.getName() + "%");
            }
            if (priceIncluded) {
                ps.setDouble(paramIdx++, dto.getPrice());
            }
            if (manufacturerIncluded) {
                ps.setString(paramIdx++, dto.getManufacturer());
            }
            if (strengthIncluded) {
                ps.setString(paramIdx++, dto.getStrength());
            }
            if (expiryDateIncluded) {
                ps.setTimestamp(paramIdx++, dto.getExpiryDate());
            }
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                do {
                    Pharmacy pharmacy = optionalUser.get().getPharmacy();
                    Shelf shelf = shelfRepository.findById(result.getInt("shelf_id"));
                    Sale sale = saleRepository.findById(result.getInt("sale_id"));
                    Medicine medicine = new Medicine(result.getInt("id"),
                            result.getString("name"),
                            result.getString("barcode"),
                            result.getString("strength"),
                            result.getString("manufacturer"),
                            result.getString("details"),
                            result.getDouble("price"),
                            result.getTimestamp("expiry_date"),
                            shelf,
                            pharmacy
                    );
                    medicines.add(medicine);
                } while (result.next());
            }
        } catch (SQLException e) {
            throw new BadRequestException("Connection error, reason - " + e.getMessage());
        }
        return medicines;
    }
}
