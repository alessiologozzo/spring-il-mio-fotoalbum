package com.org.springilmiofotoalbum;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.org.springilmiofotoalbum.auth.pojos.Role;
import com.org.springilmiofotoalbum.auth.pojos.User;
import com.org.springilmiofotoalbum.auth.services.RoleService;
import com.org.springilmiofotoalbum.auth.services.UserService;
import com.org.springilmiofotoalbum.pojos.Category;
import com.org.springilmiofotoalbum.pojos.Photo;
import com.org.springilmiofotoalbum.services.CategoryService;
import com.org.springilmiofotoalbum.services.PhotoService;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	PhotoService photoService;

	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<Role> roles = Arrays.asList(new Role(Role.USER), new Role(Role.ADMIN));
		roleService.saveAll(roles);

		List<User> users = Arrays.asList(new User("user", "user", roles.get(0)),
				new User("admin", "admin", roles.get(1)));
		userService.saveAll(users);

		List<Category> categories = Arrays.asList(new Category(Category.ARCHITETTURA),
				new Category(Category.ASTROFOTOGRAFIA), new Category(Category.DOCUMENTARIA),
				new Category(Category.FASHION), new Category(Category.FOTOGRAFIA_DI_GUERRA),
				new Category(Category.GLAMOUR), new Category(Category.MACRO), new Category(Category.MATRIMONIO),
				new Category(Category.NATURALISTICA), new Category(Category.PAESAGGISTICA), new Category(Category.REPORTAGE),
				new Category(Category.RITRATTISTICA), new Category(Category.SPORTIVA), new Category(Category.STILL_LIFE), 
				new Category(Category.STREET_PHOTOGRAPHY), new Category(Category.SUBACQUEA), new Category(Category.VIAGGIO));

		categoryService.saveAll(categories);

		List<Photo> photos = Arrays.asList(
			new Photo("Universo", "L'universo è veramente enorme! A volte dovremmo ricordarcelo.", "https://hips.hearstapps.com/hmg-prod/images/tatuaggi-con-stelle-perfetto-1619383554.jpg?crop=1xw:1xh;center,top&resize=640:*", users.get(0), categories.get(1), categories.get(2)),
			new Photo("Ritratto Ventoso", "Camminando nel bosco mi sono persa.", "https://www.weshoot.it/blog/wp-content/uploads/2015/02/Fotografia-di-ritratto-primo-piano-donna-occhi-messa-a-fuoco-tecnica-fotografica-consigli-fotografici-portrait.jpg", users.get(0), categories.get(3), categories.get(5), categories.get(11)), 
			new Photo("Monte Bianco", "C'è una funivia che ti porta quasi in cima al Monte Bianco! Pazzesco, no?", "https://www.courmayeurmontblanc.it/wp-content/uploads/2022/03/val-ferret-20201210-monte-bianco-ph-giacomo-buzio-02-web6527-e1649950854578.jpg", users.get(0), categories.get(8), categories.get(9)),
			new Photo("Delfino", "I delfini sono estremamente intelligenti. E carini.", "https://www.repstatic.it/content/localirep/img/rep/2021/01/26/120026689-5261a1c4-4c16-4daf-9728-694fe6a16319.jpg", users.get(1), categories.get(2), categories.get(15)), 
			new Photo("Islanda", "L'islanda può essere una terra inospitale, è vero. Però è estremamente affascinante.", "https://www.intermundial.it/blog/wp-content/uploads/2016/09/islanda-1080x570.jpg", users.get(1), categories.get(2), categories.get(8), categories.get(9), categories.get(16)));
		photoService.saveAll(photos);
	}
}