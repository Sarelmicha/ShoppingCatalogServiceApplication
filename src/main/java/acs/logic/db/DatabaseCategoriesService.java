package acs.logic.db;

import acs.boundary.CategoryBoundary;
import acs.dao.CategoryDao;
import acs.data.CategoryEntity;
import acs.exceptions.AlreadyExistsException;
import acs.logic.EnhancedCategoriesService;
import acs.logic.utils.CategoryConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DatabaseCategoriesService implements EnhancedCategoriesService {
	private CategoryDao categoryDao; // Data access object
	private CategoryConverter converter;

	@Autowired
	public DatabaseCategoriesService(CategoryDao categoryDao, CategoryConverter converter) {
		this.categoryDao = categoryDao;
		this.converter = converter;
	}

	@Override
	@Transactional
	public CategoryBoundary createCategory(CategoryBoundary categoryBoundary) {
		CategoryEntity categoryEntity = this.converter.toEntity(categoryBoundary);
		CategoryEntity category = categoryDao.findOneByName(categoryEntity.getName());
		if(category != null)
			throw new AlreadyExistsException("Category name already exists");
		categoryEntity = this.categoryDao.save(categoryEntity);
		return this.converter.fromEntity(categoryEntity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CategoryBoundary> getAllCategories(int size, int page, String sortBy, String sortOrder) {
				return this.categoryDao.findAll(
						PageRequest.of(page, size, Sort.Direction.valueOf(sortOrder), sortBy)).getContent()
		.stream().map(this.converter::fromEntity).collect(Collectors.toList());
	}

//	@Override
//	@Transactional(readOnly = true)
//	public CategoryBoundary getUser(String email) {
//		CategoryEntity categoryEntity = this.userDao.findById(email)
//				.orElseThrow(() -> new NotFoundException("no user found by email: " + email));
//
//		return this.converter.fromEntity(categoryEntity);
//	}



	@Override
	@Transactional
	public void deleteAllShopping() {
		this.categoryDao.deleteAll();
	}

//	@Override
//	@Transactional(readOnly = true)
//	public List<CategoryBoundary> getAllUsers(String criteriaType, String criteriaValue, int size, int page,
//											  String sortBy, String sortOrder) {
//		if (criteriaType != null && criteriaValue != null) {
//			if (criteriaType.equals(CriteriaType.BY_LAST_NAME.toString())) {
//				return this.userDao
//						.findAllByLastNameLikeIgnoreCase(criteriaValue,
//								PageRequest.of(page, size, Direction.valueOf(sortOrder), sortBy))
//						.stream().map(this.converter::fromEntity).collect(Collectors.toList());
//			} else if (criteriaType.equals(CriteriaType.BY_MINIMUM_AGE.toString())) {
//				return this.userDao
//						.findAllByAgeGreaterThan(Integer.parseInt(criteriaValue),
//								PageRequest.of(page, size, Direction.valueOf(sortOrder), sortBy))
//						.stream().map(this.converter::fromEntity).collect(Collectors.toList());
//			} else if (criteriaType.equals(CriteriaType.BY_ROLE.toString())) {
//				return findAllByRole(criteriaValue, size, page, sortBy, sortOrder);
//			}
//		}
//		return this.userDao.findAll(PageRequest.of(page, size, Direction.valueOf(sortOrder), sortBy)).getContent()
//		.stream().map(this.converter::fromEntity).collect(Collectors.toList());
//		// throw new BadRequestException();
//	}

//	public List<CategoryBoundary> findAllByRole(String criteriaValue, int size, int page, String sortBy, String sortOrder){
//		return this.userDao.findAll(PageRequest.of(page, size, Direction.valueOf(sortOrder), sortBy)).stream()
//				.filter(categoryEntity -> categoryEntity.roles.contains(criteriaValue))
//				.map(this.converter::fromEntity).collect(Collectors.toList());
//	}


}
