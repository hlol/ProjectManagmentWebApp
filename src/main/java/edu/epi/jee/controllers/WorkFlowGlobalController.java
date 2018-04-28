package edu.epi.jee.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.time.SimpleTimePeriod;
import org.kie.api.runtime.KieSession;
import org.kie.api.task.TaskService;
import org.kie.services.client.api.command.RemoteRuntimeEngine;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
//import com.soarco.facturation.TestBasic;
import edu.epi.jee.dao.ContratDao;


//import edu.epi.jee.dao.SpecificationDao;
//import edu.epi.jee.dao.ContratDao;
import edu.epi.jee.dao.CustomerDao;
import edu.epi.jee.dao.FactureDao;
import edu.epi.jee.dao.FicheDao;
import edu.epi.jee.dao.NotificationDao;
//import edu.epi.jee.dao.FactureDao;
//import edu.epi.jee.dao.FicheDao;
//import edu.epi.jee.dao.NotificationDao;



import edu.epi.jee.dao.ProductDao;
import edu.epi.jee.dao.ProjectDao;
import edu.epi.jee.dao.StatusDao;

//import edu.epi.jee.dao.StatusDao;
//import edu.epi.jee.dao.TaskDao;
import edu.epi.jee.dao.UserDao;
//import edu.epi.jee.dao.TaskDao;
import edu.epi.jee.dao.WorkerDao;
import edu.epi.jee.entities.ContratEntity;
import edu.epi.jee.entities.CustomerEntity;
import edu.epi.jee.entities.FactureEntity;
import edu.epi.jee.entities.FicheEntity;

import edu.epi.jee.entities.ProductEntity;
import edu.epi.jee.entities.ProjectEntity;
import edu.epi.jee.entities.StatusEntity;
import edu.epi.jee.entities.TaskEntity;
import java.io.FileNotFoundException;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class WorkFlowGlobalController implements Serializable{
	 private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private boolean viewChart = false ;
	private BarChartModel barModel;
	   private StreamedContent graphicText;
	   private StreamedContent chart;
	@EJB
	private ContratDao contratDao ; 
	private ContratEntity ContratEntity ;
	private String destinationContract="C:\\tmp\\";
	private List<TaskEntity>listTasks ;
	private String idImage ;
	private String FILE = "C:/tmp/PositionPdf.pdf";

	@EJB
	private ProjectDao projectDao;

   
	@EJB
	private UserDao userDao ;
        
	@EJB
	private WorkerDao workerDao ;
	
	@EJB
	private CustomerDao customerDao;

	@EJB
	private ProductDao productDao ; 
	
	private String workName;
	private String description;
	private Date debut_date;
	private Date fin_date;
	private boolean skip;
	private List<ProjectEntity> listProjects;
	private List<ProjectEntity> filterWork;
	private ProjectEntity selectedWork;
	private String choix;
	private TaskEntity selectedTask ;
	@EJB
	private FicheDao ficheDao ; 
	@EJB
	private NotificationDao notDao;
	private List<FicheEntity> listfiches;
	private FicheEntity ficheEntity = new FicheEntity();
	private FicheEntity ficheSelected = new FicheEntity() ;
	private String destination="docs/";
	 private String path ;
	 
	 private TaskEntity taskEntity ;
	 private List<TaskEntity> listtasks ;
	 private List<TaskEntity> filtertask ;
	 private TaskEntity taskSelected;
	 private boolean viewSharts;
	 private BarChartModel animatedModel2;
	 private LineChartModel animatedModel1;
	 private PieChartModel animatedModel3;
	 private BaseFont bfBold;
	 private BaseFont bf;
	 private int pageNumber = 0;
	
	@EJB
	private FactureDao factureService ;

	private FactureEntity factureEntity;
	
	private FactureEntity factureSelected ;
        private List<TaskEntity> tasksByFactures;
       /* @EJB
        private TaskDao taskService1 ;*/
        @EJB
        private StatusDao statusService ;

    public String getDestinationContract() {
        return destinationContract;
    }

    public void setDestinationContract(String destinationContract) {
        this.destinationContract = destinationContract;
    }

    public String getFILE() {
        return FILE;
    }

    public void setFILE(String FILE) {
        this.FILE = FILE;
    }

    public List<ProjectEntity> getListProjects() {
        return listProjects;
    }

    public void setListProjects(List<ProjectEntity> listProjects) {
        this.listProjects = listProjects;
    }

    public List<ProjectEntity> getFilterWork() {
        return filterWork;
    }

    public void setFilterWork(List<ProjectEntity> filterWork) {
        this.filterWork = filterWork;
    }

    public NotificationDao getNotDao() {
        return notDao;
    }

    public void setNotDao(NotificationDao notDao) {
        this.notDao = notDao;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public BaseFont getBfBold() {
        return bfBold;
    }

    public void setBfBold(BaseFont bfBold) {
        this.bfBold = bfBold;
    }

    public BaseFont getBf() {
        return bf;
    }

    public void setBf(BaseFont bf) {
        this.bf = bf;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

   /* public TaskDao getTaskService1() {
        return taskService1;
    }

    public void setTaskService1(TaskDao taskService1) {
        this.taskService1 = taskService1;
    }*/

    public StatusDao getStatusService() {
        return statusService;
    }

    public void setStatusService(StatusDao statusService) {
        this.statusService = statusService;
    }

    public ProjectDao getWorkService() {
        return workService;
    }

    public void setWorkService(ProjectDao workService) {
        this.workService = workService;
    }

    public List<StatusEntity> getListStatus() {
        return listStatus;
    }

    public void setListStatus(List<StatusEntity> listStatus) {
        this.listStatus = listStatus;
    }

    public ProjectEntity getWork() {
        return work;
    }

    public void setWork(ProjectEntity work) {
        this.work = work;
    }

    public URL getInstanceurl() {
        return instanceurl;
    }

    public void setInstanceurl(URL instanceurl) {
        this.instanceurl = instanceurl;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProcessID() {
        return processID;
    }

    public void setProcessID(String processID) {
        this.processID = processID;
    }

    public RemoteRuntimeEngine getEngine() {
        return engine;
    }

    public void setEngine(RemoteRuntimeEngine engine) {
        this.engine = engine;
    }

    public String getTaskUserId() {
        return taskUserId;
    }

    public void setTaskUserId(String taskUserId) {
        this.taskUserId = taskUserId;
    }

    public TaskService getTaskService() {
        return taskService;
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public int getNewtasksfound() {
        return newtasksfound;
    }

    public void setNewtasksfound(int newtasksfound) {
        this.newtasksfound = newtasksfound;
    }

    public KieSession getKsession() {
        return ksession;
    }

    public void setKsession(KieSession ksession) {
        this.ksession = ksession;
    }
	@EJB
	private ProjectDao workService ; 
	

	
	
	private List<StatusEntity> listStatus ; 
	private ProjectEntity work;
	private List<ProductEntity> productfilter;
	private List<ProductEntity> listProducts ;
	private List<ProductEntity> listProductWork ;
	private ProductEntity productSelected ; 
	private ProductEntity productworkSelected ; 
	private  URL instanceurl;
	private  String deploymentId = "com.soarco:SoarcoWorkflow:1.0";
	private  String user = "admin";
	private  String password = "admin";
	private  String processID = "SoarcoWorkflow.workflowSoarco";
	private  RemoteRuntimeEngine engine;
	//private TaskSummary selectedtask = null;
	private String taskUserId ; 
	private TaskService taskService =null;
	//private List<TaskSummary> tasks ; 
	private int activeIndex ;
	private int newtasksfound ;
	private  KieSession ksession;
	private boolean showStart  = false;
	private boolean showProgress  = false ;
	private boolean showcomplete  = false;
	//private TaskEntity selectedtask ;
	 private int progress;
	public ProjectEntity getSelectedWork() {
		return selectedWork;
	}
	public void setSelectedWork(ProjectEntity selectedWork) {
		this.selectedWork = selectedWork;
	}
	@PostConstruct
	public void init() {
		 TaskEntity t1 = new TaskEntity("task1",1,"convention");
		 TaskEntity t2 = new TaskEntity("task2",2,"planing");
		 TaskEntity t3 = new TaskEntity("task3",3,"confirmation");
		 TaskEntity t4 = new TaskEntity("task4",4,"associate the users");
		 TaskEntity t5 = new TaskEntity("task5",5,"work in progress");
		 TaskEntity t6 = new TaskEntity("task6",6,"terminated work");
		 /*taskDao.create(t1);
		 taskDao.create(t2);
		 taskDao.create(t3);
		 taskDao.create(t4);
		 taskDao.create(t5);
		 taskDao.create(t6);*/
		 StatusEntity s1 = new StatusEntity("start","yellow",1);
		 StatusEntity s2 = new StatusEntity("inprogress","green",2);
		 StatusEntity s3 = new StatusEntity("complete","red",3);
		 statusService.create(s1);
		 statusService.create(s2); 
		 statusService.create(s3);
		 factureEntity = new FactureEntity();
		taskEntity = new TaskEntity();
		//listtasks = taskDao.findAllTasks();
		// selectedTask = new TaskEntity() ;
		 ProjectEntity w1 =  new ProjectEntity("w1",null ,null, "w1", 1);
		 ProjectEntity w2 =  new ProjectEntity("w2",null ,null, "w2",2);
		 ProjectEntity w3 =  new ProjectEntity("w3",null ,null, "w3",3);
		 ProjectEntity w4 =  new ProjectEntity("w4",null ,null, "w4",4);
		 projectDao.create(w1);
		 projectDao.create(w2);
		 projectDao.create(w3);
		 projectDao.create(w4);
		listProjects= projectDao.findAllProjects();
		selectedWork =  new ProjectEntity();
		listfiches  = ficheDao.findAllFiches();
		ficheEntity = new FicheEntity();
		ContratEntity = new ContratEntity();
		activeIndex = 1 ;
	
        
	}
	/************* create char bar *****/
	public BarChartModel getBarModel() {
        return barModel;
    }
     
 
 
    private BarChartModel initBarModel2() {
        BarChartModel model = new BarChartModel();
/* for(TaskEntity t : taskDao.findAllTasks())
 {
        ChartSeries task = new ChartSeries();
        task.setLabel(t.getTaskName());
        task.set(t.getTaskName(),t.getNoteTask());
       System.out.println(".................");
       System.out.println(t.getTaskName());
 
       
        model.addSeries(task);
 }*/
        return model;
    }
     
    private void createBarModels() {
        createBarModel();
    }
     
    private void createBarModel() {
        barModel = initBarModel2();
         
        barModel.setTitle("Bar Chart");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Gender");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Births");
        yAxis.setMin(0);
        yAxis.setMax(100);
    }
     /**
     * @return *************/
	 public ProjectDao getProjectDao() {
        return projectDao;
    }

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public WorkerDao getWorkerDao() {
		return workerDao;
	}
	public void setWorkerDao(WorkerDao workerDao) {
		this.workerDao = workerDao;
	}
	
	public CustomerDao getCustomerDao() {
		return customerDao;
	}
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	
	
	public ProductDao getProductDao() {
		return productDao;
	}
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDebut_date() {
		return debut_date;
	}
	public void setDebut_date(Date debut_date) {
		this.debut_date = debut_date;
	}
	public Date getFin_date() {
		return fin_date;
	}
	public void setFin_date(Date fin_date) {
		this.fin_date = fin_date;
	}
	public boolean isSkip() {
		return skip;
	}
	public void setSkip(boolean skip) {
		this.skip = skip;
	}
	
	
	public String getChoix() {
		generate();
		return choix;
	}
	public void setChoix(String choix) {
		this.choix = choix;
	}
	public void redirectBychoice () throws IOException
	{ ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
             switch (selectedWork.getNumTask()) {
                 case 0:
                     //return "ficheUpload.jsf?faces-redirect=true"
                    
                     context.redirect("ficheUpload.xhtml");
                 case 1:
                     
                     
                    context.redirect("tasksProject.xhtml");
                 case 2:
                    
                     
                    context.redirect("factureView.xhtml");
                 case 3:
                    

                    context.redirect("contratUpload.xhtml");
                 case 4:
                    

                    context.redirect("addResources.xhtml");
                  case 5:
                      

                    context.redirect("generatePlaning.xhtml");
                  case 6:
                     

                    context.redirect("processManagementTasks.xhtml");
                   case 7:
                    

                    context.redirect("testProjetView.xhtml");
                      default:
                       break;
             }
	
                    context.redirect("listProjects.xhtml");
	}
	/******** create client needs*******/
	public String newFiche() throws IOException{
		readPdfContent();
		FicheEntity e1 = ficheDao.create(ficheEntity);
		ficheSelected = e1;
		selectedWork.setFiche(e1);
		selectedWork.setNumTask(selectedWork.getNumTask()+1);
		projectDao.update(selectedWork);
		listfiches = ficheDao.findAllFiches();
		
				return null;
	}
	public void upload(FileUploadEvent event) {  
	    FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");  
	    FacesContext.getCurrentInstance().addMessage(null, msg);
	    // Do what you want with the file        
	    try {
	        copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
	        ficheEntity.setDocumentUrl(destination+event.getFile().getFileName());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    

	}  

	public void copyFile(String fileName, InputStream in) {
	       try {
	    	    path = "C:/tmp/" + fileName;
	          
	            // write the inputStream to a FileOutputStream
	            OutputStream out = new FileOutputStream(new File(path));
	          
	            int read = 0;
	            byte[] bytes = new byte[1024];
	          
	            while ((read = in.read(bytes)) != -1) {
	                out.write(bytes, 0, read);
	            }
	          
	            in.close();
	            out.flush();
	            out.close();
	          
	            System.out.println("New file created!");
	            } catch (IOException e) {
	            System.out.println(e.getMessage());
	            }
	}
	public FicheDao getFicheDao() {
		return ficheDao;
	}
	public void setFicheDao(FicheDao ficheDao) {
		this.ficheDao = ficheDao;
	}
	public List<FicheEntity> getListfiches() {
		return listfiches;
	}
	public void setListfiches(List<FicheEntity> listfiches) {
		this.listfiches = listfiches;
	}
	public FicheEntity getFicheEntity() {
		return ficheEntity;
	}
	public void setFicheEntity(FicheEntity ficheEntity) {
		this.ficheEntity = ficheEntity;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public FicheEntity getFicheSelected() {
		return ficheSelected;
	}
	public void setFicheSelected(FicheEntity ficheSelected) {
		this.ficheSelected = ficheSelected;
	}
	public String redirectToUpdate()
	{
		ficheEntity = ficheSelected;
		return "ficheUpload.jsf?faces-redirect=true";
	}
	public String deleteFiche()
	{
	//	ficheDao.delete(ficheSelected.getId_fiche());
		ficheEntity = new FicheEntity();
		return  "ficheUpload.jsf?faces-redirect=true";
	}
	public void downloadPdf() throws IOException {
		String PDF_URL = "file:/"+path;
		URL url1 = new URL("file:/E:/tmp/cars.pdf");
		System.out.println(url1.getFile());  // "/tmp/foo.txt"
		File file = new File(url1.getFile());
		System.out.print("*********************");
		System.out.println(file.exists()); // false
		// Get the FacesContext
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		// Get HTTP response
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		
		// Set response headers
		response.reset();	// Reset the response in the first place
        response.setHeader("Content-Type", "application/pdf");	// Set only the content type
        
		// Open response output stream
		OutputStream responseOutputStream = response.getOutputStream();
		
		// Read PDF contents
		URL url = new URL(PDF_URL);
        InputStream pdfInputStream = url.openStream();
        
        // Read PDF contents and write them to the output
        byte[] bytesBuffer = new byte[2048];
        int bytesRead;
        while ((bytesRead = pdfInputStream.read(bytesBuffer)) > 0) {
        	responseOutputStream.write(bytesBuffer, 0, bytesRead);
        }
        
        // Make sure that everything is out
        responseOutputStream.flush();
         
        // Close both streams
        pdfInputStream.close();
        responseOutputStream.close();
        
        // JSF doc: 
        // Signal the JavaServer Faces implementation that the HTTP response for this request has already been generated 
        // (such as an HTTP redirect), and that the request processing lifecycle should be terminated
        // as soon as the current phase is completed.
        facesContext.responseComplete();
        
	}
	public  void readPdfContent() throws IOException
	{
		String  ch ="" ;
		PdfReader reader = new PdfReader("file:/C:/tmp/cars.pdf");
          System.out.println("************************"+reader.getPdfVersion());
		/*
          PdfReaderContentParser parser = new

		   PdfReaderContentParser(reader);

		TextExtractionStrategy strategy = null;

		for(int i = 0; i <= reader.getNumberOfPages(); i++) {

		       strategy = parser.processContent(i,new SimpleTextExtractionStrategy());

		       System.out.println(strategy.getResultantText()+"--------");
ch = ch + strategy.getResultantText() ;
*/
          String textFromPage = PdfTextExtractor.getTextFromPage(reader, 1);

          System.out.println(textFromPage+".....................");

          reader.close();

		
		ficheEntity.setContent(textFromPage.substring(0, 100));
	}
	
	/****************************/
	/******** tasks Management *******/
	/*public String newtask(){
		TaskEntity c1 = taskDao.create(taskEntity);
		listtasks = taskDao.findAllTasks();
		selectedWork.setTasks(listTasks);
		selectedWork.setNumTask(2);
		selectedWork = projectDao.update(selectedWork);
		taskEntity = new TaskEntity();
		return null;
	}*/

/**************/
	/********* test phase ***********/
	public String viewDashbord()
	{
		 createAnimatedModels();
		 createBarModels();
		 viewChart =true;
		return null;
	}
	private void createAnimatedModels() {
	    animatedModel1 = initLinearModel();
	    animatedModel1.setTitle("Line Chart");
	    animatedModel1.setAnimate(true);
	    animatedModel1.setLegendPosition("se");
	    Axis yAxis = animatedModel1.getAxis(AxisType.Y);
	    yAxis.setMin(0);
	    yAxis.setMax(10);
	     
	    animatedModel2 = initBarModel();
	    animatedModel2.setTitle("Bar Charts");
	    animatedModel2.setAnimate(true);
	    animatedModel2.setLegendPosition("ne");
	    yAxis = animatedModel2.getAxis(AxisType.Y);
	    yAxis.setMin(0);
	    yAxis.setMax(200);
	    
	  //  animatedModel3 = createPieModel1();
	    animatedModel3.setTitle("Bar Charts");
	    animatedModel3.setLegendPosition("ne");
	    yAxis = animatedModel2.getAxis(AxisType.Y);
	    yAxis.setMin(0);
	    yAxis.setMax(200);
	    
	}
	 
	private BarChartModel initBarModel() {
	    BarChartModel model = new BarChartModel();

	    ChartSeries boys = new ChartSeries();
	    boys.setLabel("Boys");
	    boys.set("2004", 120);
	    boys.set("2005", 100);
	    boys.set("2006", 44);
	    boys.set("2007", 150);
	    boys.set("2008", 25);

	    ChartSeries girls = new ChartSeries();
	    girls.setLabel("Girls");
	    girls.set("2004", 52);
	    girls.set("2005", 60);
	    girls.set("2006", 110);
	    girls.set("2007", 135);
	    girls.set("2008", 120);

	    model.addSeries(boys);
	    model.addSeries(girls);
	     
	    return model;
	}
	 
	private LineChartModel initLinearModel() {
	    LineChartModel model = new LineChartModel();

	    LineChartSeries series1 = new LineChartSeries();
	    series1.setLabel("Series 1");

	    series1.set(1, 2);
	    series1.set(2, 1);
	    series1.set(3, 3);
	    series1.set(4, 6);
	    series1.set(5, 8);

	    LineChartSeries series2 = new LineChartSeries();
	    series2.setLabel("Series 2");

	    series2.set(1, 6);
	    series2.set(2, 3);
	    series2.set(3, 2);
	    series2.set(4, 7);
	    series2.set(5, 9);

	    model.addSeries(series1);
	    model.addSeries(series2);
	     
	    return model;
	}
	public LineChartModel getAnimatedModel1() {
		return animatedModel1;
	}
	public void setAnimatedModel1(LineChartModel animatedModel1) {
		this.animatedModel1 = animatedModel1;
	}
	/*private PieChartModel createPieModel1() {
		PieChartModel pieModel1 = new PieChartModel();
		float nots = 0;
	   for(TaskEntity t : taskDao.findAllTasks())  
	   {
		   nots = nots+t.getNoteTask();
		   
	   }
	   float moy =nots/taskDao.findAllTasks().size() ;
	    pieModel1.set("realis�", moy);
	    pieModel1.set("non realis�",100-moy);
	     
	    pieModel1.setTitle("Simple Pie");
	    pieModel1.setLegendPosition("w");
	    return pieModel1;
	}*/
	/********************************/
	public TaskEntity getTaskEntity() {
		return taskEntity;
	}
	public void setTaskEntity(TaskEntity taskEntity) {
		this.taskEntity = taskEntity;
	}
	public List<TaskEntity> getListtasks() {
		return listtasks;
	}
	public void setListtasks(List<TaskEntity> listtasks) {
		this.listtasks = listtasks;
	}
	public boolean isViewSharts() {
		return viewSharts;
	}
	public void setViewSharts(boolean viewSharts) {
		this.viewSharts = viewSharts;
	}
	public BarChartModel getAnimatedModel2() {
		return animatedModel2;
	}
	public void setAnimatedModel2(BarChartModel animatedModel2) {
		this.animatedModel2 = animatedModel2;
	}
	
	public PieChartModel getAnimatedModel3() {
		return animatedModel3;
	}
	public void setAnimatedModel3(PieChartModel animatedModel3) {
		this.animatedModel3 = animatedModel3;
	}
	public FactureEntity getFactureSelected() {
		return factureSelected;
	}

	public void setFactureSelected(FactureEntity factureSelected) {
		this.factureSelected = factureSelected;
	}

	public FactureDao getFactureService() {
		return factureService;
	}
	public void setFactureService(FactureDao factureService) {
		this.factureService = factureService;
	}
	public FactureEntity getFactureEntity() {
		return factureEntity;
	}
	public void setFactureEntity(FactureEntity factureEntity) {
		this.factureEntity = factureEntity;
	}
	public List<TaskEntity> getTasksByFactures() {
		return tasksByFactures;
	}
	public void setTasksByFactures(List<TaskEntity> tasksByFactures) {
		this.tasksByFactures = tasksByFactures;
	}
/**************** add facture ****************/
	public String newFacture() throws FileNotFoundException, IOException
	{
	//	TestBasic generateInvoice = new TestBasic();
	  String pdfFilename = "sdkjdslk.pdf";
	 // createPDF(pdfFilename);
	  selectedWork.setNumTask(selectedWork.getNumTask()+1);
		return "factureView?faces-redirect=true" ;
	}
	/*public  void createPDF (String pdfFilename) throws MalformedURLException, FileNotFoundException, IOException{
		DecimalFormat df = new DecimalFormat("0.00");
		  Document doc = new Document();
		  PdfWriter docWriter = null;
		  initializeFonts();
		 
		  try {
		//   String path = "docs/" + pdfFilename;
			  String path = "file:/E:/tmp/facture.pdf" ;
			  URL url1 = new URL("file:/E:/tmp/facture.pdf");
		   docWriter = PdfWriter.getInstance(doc , new FileOutputStream(url1.getFile()));
		   doc.addAuthor("betterThanZero");
		   doc.addCreationDate();
		   doc.addProducer();
		   doc.addCreator("MySampleCode.com");
		   doc.addTitle("Invoice");
		   doc.setPageSize(PageSize.LETTER);
		 
		   doc.open();
		   PdfContentByte cb = docWriter.getDirectContent();
		    
		   boolean beginPage = true;
		   int y = 0;
		    
		   //for(int i=0; i < 10; i++ ){
		   for(TaskEntity t : taskDao.findAllTasks()) 
		   {
		   if(beginPage){
		     beginPage = false;
		     generateLayout(doc, cb); 
		     generateHeader(doc, cb);
		     y = 615; 
		    
		    }
		  //  generateDetail(doc, cb, i, y);
		    try {
				 
				   createContent(cb,48,y,t.getNumber()+"",PdfContentByte.ALIGN_RIGHT);
				   createContent(cb,52,y, t.getTaskName(),PdfContentByte.ALIGN_LEFT);
				   createContent(cb,152,y, t.getDescription() ,PdfContentByte.ALIGN_LEFT);
				  
				 //  double price = Double.valueOf(df.format(Math.random() * 10));
				   double extPrice = t.getCostTask() ;
				   createContent(cb,498,y, df.format(extPrice),PdfContentByte.ALIGN_RIGHT);
				   createContent(cb,568,y, df.format(extPrice),PdfContentByte.ALIGN_RIGHT);
				    
				  }
				 
				  catch (Exception ex){
				   ex.printStackTrace();
				  }
		    y = y - 15;
		  
		    if(y < 50){
		     printPageNumber(cb);
		     doc.newPage();
		     beginPage = true;
		    }
		   }
		   // facture elements
		    try {
				 //brut
				   createContent(cb,48,y,"",PdfContentByte.ALIGN_RIGHT);
				   createContent(cb,52,y,"BRUT HT",PdfContentByte.ALIGN_LEFT);
				   createContent(cb,152,y, "somme des montants bruts",PdfContentByte.ALIGN_LEFT);
				  
				 //  double price = Double.valueOf(df.format(Math.random() * 10));
				   double extPrice = factureEntity.getBrutHt();
				   createContent(cb,498,y, df.format(extPrice),PdfContentByte.ALIGN_RIGHT);
				   createContent(cb,568,y, "",PdfContentByte.ALIGN_RIGHT);
				   y = y - 15;
				   //remise
				   createContent(cb,48,y,"",PdfContentByte.ALIGN_RIGHT);
				   createContent(cb,52,y,"Remise",PdfContentByte.ALIGN_LEFT);
				   createContent(cb,152,y, "montant brut x taux de remise",PdfContentByte.ALIGN_LEFT);
				  
				 //  double price = Double.valueOf(df.format(Math.random() * 10));
				   double remise = factureEntity.getRemise();
				   createContent(cb,498,y, df.format(remise),PdfContentByte.ALIGN_RIGHT);
				   createContent(cb,568,y, "",PdfContentByte.ALIGN_RIGHT);
				   //net commercial HT
				   createContent(cb,48,y,"",PdfContentByte.ALIGN_RIGHT);
				   createContent(cb,52,y,"net commercial HT",PdfContentByte.ALIGN_LEFT);
				   createContent(cb,152,y, "Brut reductions commerciales",PdfContentByte.ALIGN_LEFT);
				  
				 //  double price = Double.valueOf(df.format(Math.random() * 10));
				   double netcommercial = factureEntity.getNetHt();
				   createContent(cb,498,y, df.format(netcommercial),PdfContentByte.ALIGN_RIGHT);
				   createContent(cb,568,y, "",PdfContentByte.ALIGN_RIGHT);
				   y = y - 15;
				   //Escompte
				   createContent(cb,48,y,"",PdfContentByte.ALIGN_RIGHT);
				   createContent(cb,52,y,"Escompte",PdfContentByte.ALIGN_LEFT);
				   createContent(cb,152,y, "Net Commercial x taux d'escompte",PdfContentByte.ALIGN_LEFT);
				  
				 //  double price = Double.valueOf(df.format(Math.random() * 10));
				   double escompte = factureEntity.getEscompte();
				   createContent(cb,498,y, df.format(escompte),PdfContentByte.ALIGN_RIGHT);
				   createContent(cb,568,y, "",PdfContentByte.ALIGN_RIGHT);
				   y = y - 15;
				   //net financier
				   createContent(cb,48,y,"",PdfContentByte.ALIGN_RIGHT);
				   createContent(cb,52,y,"net financier",PdfContentByte.ALIGN_LEFT);
				   createContent(cb,152,y, "net commercial - escompte",PdfContentByte.ALIGN_LEFT);
				  
				 //  double price = Double.valueOf(df.format(Math.random() * 10));
				   double netFinancier = factureEntity.getNetFinancierHt();
				   createContent(cb,498,y, df.format(netFinancier),PdfContentByte.ALIGN_RIGHT);
				   createContent(cb,568,y, "",PdfContentByte.ALIGN_RIGHT);
				   y = y - 15;
				   //portForfaitaire
				   createContent(cb,48,y,"",PdfContentByte.ALIGN_RIGHT);
				   createContent(cb,52,y,"portForfaitaire",PdfContentByte.ALIGN_LEFT);
				   createContent(cb,152,y, "portForfaitaire",PdfContentByte.ALIGN_LEFT);
				  
				 //  double price = Double.valueOf(df.format(Math.random() * 10));
				   double portForfaitaire = factureEntity.getForfaitaire();
				   createContent(cb,498,y, df.format(portForfaitaire),PdfContentByte.ALIGN_RIGHT);
				   createContent(cb,568,y, "",PdfContentByte.ALIGN_RIGHT);
				   y = y - 15;
				   //netHT
				   createContent(cb,48,y,"",PdfContentByte.ALIGN_RIGHT);
				   createContent(cb,52,y,"netHT",PdfContentByte.ALIGN_LEFT);
				   createContent(cb,152,y, "Net Financier + port",PdfContentByte.ALIGN_LEFT);
				  
				 //  double price = Double.valueOf(df.format(Math.random() * 10));
				   double netHT = factureEntity.getNetHt();
				   createContent(cb,498,y, df.format(netHT),PdfContentByte.ALIGN_RIGHT);
				   createContent(cb,568,y, "",PdfContentByte.ALIGN_RIGHT);
				   y = y - 15;
				   //TVA
				   createContent(cb,48,y,"",PdfContentByte.ALIGN_RIGHT);
				   createContent(cb,52,y,"TVA",PdfContentByte.ALIGN_LEFT);
				   createContent(cb,152,y, "Net HT * Taux de tva",PdfContentByte.ALIGN_LEFT);
				  
				 //  double price = Double.valueOf(df.format(Math.random() * 10));
				   double TVA = factureEntity.getTVA();
				   createContent(cb,498,y, df.format(TVA),PdfContentByte.ALIGN_RIGHT);
				   createContent(cb,568,y, "",PdfContentByte.ALIGN_RIGHT);
				   y = y - 15;
				   //netTTC
				   createContent(cb,48,y,"",PdfContentByte.ALIGN_RIGHT);
				   createContent(cb,52,y,"netTTC",PdfContentByte.ALIGN_LEFT);
				   createContent(cb,152,y, "Net HT * Taux de tva",PdfContentByte.ALIGN_LEFT);
				  
				 //  double price = Double.valueOf(df.format(Math.random() * 10));
				   double netTTC = factureEntity.getNetTTC();
				   createContent(cb,498,y, df.format(netTTC),PdfContentByte.ALIGN_RIGHT);
				   createContent(cb,568,y, "",PdfContentByte.ALIGN_RIGHT);
				   y = y - 15;
				   //AcompteVerse
				   createContent(cb,48,y,"",PdfContentByte.ALIGN_RIGHT);
				   createContent(cb,52,y,"AcompteVerse",PdfContentByte.ALIGN_LEFT);
				   createContent(cb,152,y, "Net TTC - accompte vers�",PdfContentByte.ALIGN_LEFT);
				  
				 //  double price = Double.valueOf(df.format(Math.random() * 10));
				   double AcompteVerse = factureEntity.getAcompteVerse();
				   createContent(cb,498,y, df.format(AcompteVerse),PdfContentByte.ALIGN_RIGHT);
				   createContent(cb,568,y, "",PdfContentByte.ALIGN_RIGHT);
				   y = y - 15;
				   //netPayerTTC
				   createContent(cb,48,y,"",PdfContentByte.ALIGN_RIGHT);
				   createContent(cb,52,y,"netPayerTTC",PdfContentByte.ALIGN_LEFT);
				   createContent(cb,152,y, "netPayerTTC",PdfContentByte.ALIGN_LEFT);
				  
				 //  double price = Double.valueOf(df.format(Math.random() * 10));
				   double netPayerTTC = factureEntity.getNetPayerTTC();
				   createContent(cb,498,y, df.format(netPayerTTC),PdfContentByte.ALIGN_RIGHT);
				   createContent(cb,568,y, "",PdfContentByte.ALIGN_RIGHT);
				   y = y - 15; 
				  }
				 
				  catch (Exception ex){
				   ex.printStackTrace();
				  }  
	       
		   printPageNumber(cb);
		   
		  }
		  catch (DocumentException dex)
		  {
		   dex.printStackTrace();
		  }
		  finally
		  {
		   if (doc != null)
		   {
		    doc.close();
		   }
		   if (docWriter != null)
		   {
		    docWriter.close();
		   }
		  }
		 }*/
		 
		 public void generateLayout(Document doc, PdfContentByte cb)  {
		 
		  try {
		 
		   cb.setLineWidth(1f);
		 
		   // Invoice Header box layout
		   cb.rectangle(420,700,150,60);
		   cb.moveTo(420,720);
		   cb.lineTo(570,720);
		   cb.moveTo(420,740);
		   cb.lineTo(570,740);
		   cb.moveTo(480,700);
		   cb.lineTo(480,760);
		   cb.stroke();
		 
		   // Invoice Header box Text Headings 
		   createHeadings(cb,422,743,"Account No.");
		   createHeadings(cb,422,723,"Invoice No.");
		   createHeadings(cb,422,703,"Invoice Date");
		 
		   // Invoice Detail box layout 
		   cb.rectangle(20,50,550,600);
		   cb.moveTo(20,630);
		   cb.lineTo(570,630);
		   cb.moveTo(50,50);
		   cb.lineTo(50,650);
		   cb.moveTo(150,50);
		   cb.lineTo(150,650);
		   cb.moveTo(430,50);
		   cb.lineTo(430,650);
		   cb.moveTo(500,50);
		   cb.lineTo(500,650);
		   cb.stroke();
		 
		   // Invoice Detail box Text Headings 
		   createHeadings(cb,22,633,"Qty");
		   createHeadings(cb,52,633,"Item Number");
		   createHeadings(cb,152,633,"Item Description");
		   createHeadings(cb,432,633,"Price");
		   createHeadings(cb,502,633,"Ext Price");
		 
		   //add the images
		  
		   Image companyLogo = Image.getInstance("E:\\tmp\\logo1.jpeg");
		   companyLogo.setAbsolutePosition(25,700);
		   companyLogo.scalePercent(25);
		   doc.add(companyLogo);
		 
		  }
		 
		  catch (Exception ex){
		   ex.printStackTrace();
		  }
		 
		 }
		  
		 public void generateHeader(Document doc, PdfContentByte cb)  {
		 
		  try {
		 
		   createHeadings(cb,200,750,"Company Name");
		   createHeadings(cb,200,735,"Address Line 1");
		   createHeadings(cb,200,720,"Address Line 2");
		   createHeadings(cb,200,705,"City, State - ZipCode");
		   createHeadings(cb,200,690,"Country");
		    
		   createHeadings(cb,482,743,"ABC0001");
		   createHeadings(cb,482,723,"123456");
		   createHeadings(cb,482,703,"09/26/2012");
		 
		  }
		 
		  catch (Exception ex){
		   ex.printStackTrace();
		  }
		 
		 }
		  
		 private void generateDetail(Document doc, PdfContentByte cb, int index, int y)  {
		  DecimalFormat df = new DecimalFormat("0.00");
		   
		  try {
		 
		   createContent(cb,48,y,String.valueOf(index+1),PdfContentByte.ALIGN_RIGHT);
		   createContent(cb,52,y, "ITEM" + String.valueOf(index+1),PdfContentByte.ALIGN_LEFT);
		   createContent(cb,152,y, "Product Description - SIZE " + String.valueOf(index+1),PdfContentByte.ALIGN_LEFT);
		  
		 //  double price = Double.valueOf(df.format(Math.random() * 10));
		   double extPrice = 11 ;
		   createContent(cb,498,y, df.format(11),PdfContentByte.ALIGN_RIGHT);
		   createContent(cb,568,y, df.format(extPrice),PdfContentByte.ALIGN_RIGHT);
		    
		  }
		 
		  catch (Exception ex){
		   ex.printStackTrace();
		  }
		 
		 }
		 
		 private void createHeadings(PdfContentByte cb, float x, float y, String text){
		 
		 
		  cb.beginText();
		  cb.setFontAndSize(bfBold, 8);
		  cb.setTextMatrix(x,y);
		  cb.showText(text.trim());
		  cb.endText(); 
		 
		 }
		  
		 private void printPageNumber(PdfContentByte cb){
		 
		 
		  cb.beginText();
		  cb.setFontAndSize(bfBold, 8);
		  cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Page No. " + (pageNumber+1), 570 , 25, 0);
		  cb.endText(); 
		   
		  pageNumber++;
		   
		 }
		  
		 private void createContent(PdfContentByte cb, float x, float y, String text, int align){
		 
		 
		  cb.beginText();
		  cb.setFontAndSize(bf, 8);
		  cb.showTextAligned(align, text.trim(), x , y, 0);
		  cb.endText(); 
		 
		 }
		 
		 private void initializeFonts() throws IOException{
		 
		 
		  try {
		   bfBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		   bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		 
		  } catch (DocumentException e) {
		   e.printStackTrace();
		  }
		 }
	/*********************************************/
		
		 public ContratDao getContratDao() {
				return contratDao;
			}
			public void setContratDao(ContratDao contratDao) {
				this.contratDao = contratDao;
			}
			
			public ContratEntity getContratEntity() {
				return ContratEntity;
			}
			public void setContratEntity(ContratEntity contratEntity) {
				ContratEntity = contratEntity;
			}
			 /***************** generate Contract ***************/
			public String generateContact()
		{
			try
			{
			Document document = new Document();
		    PdfWriter.getInstance(document, new FileOutputStream(FILE));
		    document.open();
		    Image companyLogo = Image.getInstance("E:\\tmp\\logo1.jpeg");
			   companyLogo.setAbsolutePosition(25,700);
			   companyLogo.scalePercent(25);
			   document.add(companyLogo);
		    // Left
		    Paragraph paragraph = new Paragraph(ContratEntity.getCreationDate().toString());
		    paragraph.setAlignment(Element.ALIGN_RIGHT);
		    document.add(paragraph);
		    // Centered
		    paragraph = new Paragraph("Contrat de travail");
		    paragraph.setAlignment(Element.ALIGN_CENTER);
		    document.add(paragraph);
		    // Left
		    paragraph = new Paragraph("Entre ");
		    paragraph.add("name client ");
		    paragraph.add("soarco ");
		    paragraph.setAlignment(Element.ALIGN_CENTER);
		    document.add(paragraph);
		    paragraph = new Paragraph("Les taches � faire");
		    for (TaskEntity t : listTasks)
		    {
		    paragraph.add(t.getNumber()+"-"+t.getTaskName()+" : "+ t.getDescription()+"\n");
		    }
		    paragraph.setAlignment(Element.ALIGN_LEFT);
		    document.add(paragraph);
		    //table
		    PdfPTable table = new PdfPTable(3);

		    // t.setBorderColor(BaseColor.GRAY);
		    // t.setPadding(4);
		    // t.setSpacing(4);
		    // t.setBorderWidth(1);

		    PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		    table.addCell(c1);

		    c1 = new PdfPCell(new Phrase("Table Header 2"));
		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		    table.addCell(c1);

		    c1 = new PdfPCell(new Phrase("Table Header 3"));
		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		    table.addCell(c1);
		    table.setHeaderRows(1);

		    table.addCell("1.0");
		    table.addCell("1.1");
		    table.addCell("1.2");
		    table.addCell("2.1");
		    table.addCell("2.2");
		    table.addCell("2.3");
		    document.add(table);
		    // Left with indentation
		    paragraph = new Paragraph(
		                    "This is left aligned text with indentation");
		    paragraph.setAlignment(Element.ALIGN_LEFT);
		    paragraph.setIndentationLeft(50);
		    document.add(paragraph);

		    document.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
			selectedWork.setNumTask(selectedWork.getNumTask()+1);
			return "contratUpload.jsf?faces-redirect=true";
		}

		/**************************************************/
		public String getdestinationContract() {
			return destinationContract;
		}
		public void setdestinationContract(String destinationContract) {
			this.destinationContract = destinationContract;
		}
		public String onFlowProcess(FlowEvent event) {
		    if(skip) {
		        skip = false;   //reset in case user goes back
		        return "confirm";
		    }
		    else {
		        return event.getNewStep();
		    }
		}
		public String deletetask(){
			return null ;
			
		}
		/*public String updatetask(){
			TaskEntity c2 = taskDao.update(taskSelected);
			listTasks = taskDao.findAllTasks();
			return null ;
		}
		public TaskDao getTaskDao() {
			return taskDao;
		}
		public void setTaskDao(TaskDao taskDao) {
			this.taskDao = taskDao;
		}*/
		
		public TaskEntity getTaskSelected() {
			return taskSelected;
		}
		public void setTaskSelected(TaskEntity taskSelected) {
			this.taskSelected = taskSelected;
		}
		public List<TaskEntity> getFiltertask() {
			return filtertask;
		}
		public void setFiltertask(List<TaskEntity> filtertask) {
			this.filtertask = filtertask;
		}
		public String saveResources()
		{
			selectedWork.setNumTask(selectedWork.getNumTask()+1);
			return null ;
		}
	    /****
	     * generate gantt diagramme
	     */
	 
	   /**************************************************/
	   public List<TaskEntity> getListTasks() {
			return listTasks;
		}
		public void setListTasks(List<TaskEntity> listTasks) {
			this.listTasks = listTasks;
		}

		public boolean isShowStart() {
			return showStart;
		}
		public void setShowStart(boolean showStart) {
			this.showStart = showStart;
		}
		public boolean isShowProgress() {
			return showProgress;
		}
		public void setShowProgress(boolean showProgress) {
			this.showProgress = showProgress;
		}
		public boolean isShowcomplete() {
			return showcomplete;
		}
		public void setShowcomplete(boolean showcomplete) {
			this.showcomplete = showcomplete;
		}
		
		public String  onStart()
		{
			 showStart  = true;
			 showProgress  = false;
			showcomplete  = true;
			return null ;
		}
		public String inprogressTask(){
			 Date date = new Date();
				
		        System.out.println("0.0.0.0.0.0.0..0.0.0.0..0..0.0.0.0.0.0.0.0.0.0"+sdf.format(date));
					selectedTask.setBeginDate(date);
					 System.out.println("0.0.0.0.0.0.0..0.0.0.0..0..0.0.0.0.0.0.0.0.0.0"+selectedTask.getBeginDate());
					// selectedTask =taskDao.update(selectedTask);
			System.out.println("/***/*/*/*/*/*/**/**/**/"+selectedTask.getBeginDate());
			 showStart  = true;
			showProgress  = true;
			 showcomplete  = false;
		return null ;
		}

		public String completeTask()
		{
			 Date date = new Date();
		        System.out.println(sdf.format(date));
					selectedTask.setEndDate(date);
				//	taskDao.update(selectedTask);
			 showStart  = false;
			showProgress  = true;
			 showcomplete  = true;
			 activeIndex= activeIndex + 1 ; 
		//long diffInMillies = selectedTask.getBeginDate().getTime() - selectedTask.getEndDate().getTime();
	//	System.out.println("*--*-*-*-*--*-*-*-*--**-*-*-**---**-*-*--**--*-"+diffInMillies);
		return null ;
		}
		  public int getProgress() {
			    
	          progress =activeIndex * 100 / listtasks.size() ; 
	         
	        return progress;
	    }
	public void addCustomer ()
	{
		CustomerEntity c = new CustomerEntity();
		customerDao.create(c);
		selectedWork.setCustomer(c);
		ProjectEntity w1 = projectDao.update(selectedWork);
		System.out.println("-.-.-.--.-.-.-.-.-.--.-."+w1.getCustomer().getIdCustomer());
	}
	public String addProductToWork(){
		
		System.out.println("***************************************");
		System.out.println("---"+productSelected.getIdProduct());
		
		listProductWork.add(productSelected);
		if(listProducts!=null)
		listProducts.remove(productSelected);

		return null ;
	}

public String deleteProductFromWork()
{
	
	System.out.println("***************************************");
	System.out.println("***"+productworkSelected.getIdProduct());
	
	
	listProducts.add(productworkSelected);
	if (listProductWork!=null)
		listProductWork.remove(productworkSelected);
	
	return null ;
}


public void generate()
{
	try {
      
        //Chart
    //    JFreeChart jfreechart = ChartFactory.createPieChart("Cities", createDataset(), true, true, false);
		
			System.out.println("/*/*/*/*/**/*/*/*/**/*/*/*////**/*/*/*/*/*/*");
		File chartFile = new File("dynamichart"+idImage);

        ChartUtilities.saveChartAsPNG(chartFile, createChart(createDataset1()), 640, 480);
        chart = new DefaultStreamedContent(new FileInputStream(chartFile), "image/png");
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String idImage = externalContext.getRequestParameterMap().get("photo_id");
        chartFile.delete();
		} 
    catch (Exception e) {
        e.printStackTrace();
    }
}
	public StreamedContent getGraphicText() {
        return graphicText;
    }
         
    public StreamedContent getChart() {
    	generate();
        return chart;
    }
     
    private PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("New York", new Double(45.0));
        dataset.setValue("London", new Double(15.0));
        dataset.setValue("Paris", new Double(25.2));
        dataset.setValue("Berlin", new Double(14.8));
 
        return dataset;
    }
    /****
     * generate gantt diagramme
     */
    private static Date date(final int day, final int month, final int year) {

        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        final Date result = calendar.getTime();
        return result;

    }
    public IntervalCategoryDataset createDataset1() {

        final TaskSeries s1 = new TaskSeries("Scheduled");
      /*  int i =1;
        for(TaskEntity t : taskDao.findAllTasks())
        {
        	
        	 s1.add(new Task(t.getTaskName(),
                     new SimpleTimePeriod(date(0, i, 2017),
                                          date(t.getEstimatePoint(),i+t.getEstimatePoint()/30, 2017))));
        	 i++;
        }
        */
        s1.add(new Task("Write Proposal",
               new SimpleTimePeriod(date(1, Calendar.APRIL, 2001),
                                    date(5, Calendar.APRIL, 2001))));
        s1.add(new Task("Obtain Approval",
               new SimpleTimePeriod(date(9, Calendar.APRIL, 2001),
                                    date(9, Calendar.APRIL, 2001))));
        s1.add(new Task("Requirements Analysis",
               new SimpleTimePeriod(date(10, Calendar.APRIL, 2001),
                                    date(5, Calendar.MAY, 2001))));
        s1.add(new Task("Design Phase",
               new SimpleTimePeriod(date(6, Calendar.MAY, 2001),
                                    date(30, Calendar.MAY, 2001))));
        s1.add(new Task("Design Signoff",
               new SimpleTimePeriod(date(2, Calendar.JUNE, 2001),
                                    date(2, Calendar.JUNE, 2001))));
        s1.add(new Task("Alpha Implementation",
               new SimpleTimePeriod(date(3, Calendar.JUNE, 2001),
                                    date(31, Calendar.JULY, 2001))));
        s1.add(new Task("Design Review",
               new SimpleTimePeriod(date(1, Calendar.AUGUST, 2001),
                                    date(8, Calendar.AUGUST, 2001))));
        s1.add(new Task("Revised Design Signoff",
               new SimpleTimePeriod(date(10, Calendar.AUGUST, 2001),
                                    date(10, Calendar.AUGUST, 2001))));
        s1.add(new Task("Beta Implementation",
               new SimpleTimePeriod(date(12, Calendar.AUGUST, 2001),
                                    date(12, Calendar.SEPTEMBER, 2001))));
        s1.add(new Task("Testing",
               new SimpleTimePeriod(date(13, Calendar.SEPTEMBER, 2001),
                                    date(31, Calendar.OCTOBER, 2001))));
        s1.add(new Task("Final Implementation",
               new SimpleTimePeriod(date(1, Calendar.NOVEMBER, 2001),
                                    date(15, Calendar.NOVEMBER, 2001))));
        s1.add(new Task("Signoff",
               new SimpleTimePeriod(date(28, Calendar.NOVEMBER, 2001),
                                    date(30, Calendar.NOVEMBER, 2001))));

        final TaskSeries s2 = new TaskSeries("Actual");
        s2.add(new Task("Write Proposal",
               new SimpleTimePeriod(date(1, Calendar.APRIL, 2001),
                                    date(5, Calendar.APRIL, 2001))));
        s2.add(new Task("Obtain Approval",
               new SimpleTimePeriod(date(9, Calendar.APRIL, 2001),
                                    date(9, Calendar.APRIL, 2001))));
        s2.add(new Task("Requirements Analysis",
               new SimpleTimePeriod(date(10, Calendar.APRIL, 2001),
                                    date(15, Calendar.MAY, 2001))));
        s2.add(new Task("Design Phase",
               new SimpleTimePeriod(date(15, Calendar.MAY, 2001),
                                    date(17, Calendar.JUNE, 2001))));
        s2.add(new Task("Design Signoff",
               new SimpleTimePeriod(date(30, Calendar.JUNE, 2001),
                                    date(30, Calendar.JUNE, 2001))));
        s2.add(new Task("Alpha Implementation",
               new SimpleTimePeriod(date(1, Calendar.JULY, 2001),
                                    date(12, Calendar.SEPTEMBER, 2001))));
        s2.add(new Task("Design Review",
               new SimpleTimePeriod(date(12, Calendar.SEPTEMBER, 2001),
                                    date(22, Calendar.SEPTEMBER, 2001))));
        s2.add(new Task("Revised Design Signoff",
               new SimpleTimePeriod(date(25, Calendar.SEPTEMBER, 2001),
                                    date(27, Calendar.SEPTEMBER, 2001))));
        s2.add(new Task("Beta Implementation",
               new SimpleTimePeriod(date(27, Calendar.SEPTEMBER, 2001),
                                    date(30, Calendar.OCTOBER, 2001))));
        s2.add(new Task("Testing",
               new SimpleTimePeriod(date(31, Calendar.OCTOBER, 2001),
                                    date(17, Calendar.NOVEMBER, 2001))));
        s2.add(new Task("Final Implementation",
               new SimpleTimePeriod(date(18, Calendar.NOVEMBER, 2001),
                                    date(5, Calendar.DECEMBER, 2001))));
        s2.add(new Task("Signoff",
               new SimpleTimePeriod(date(10, Calendar.DECEMBER, 2001),
                                    date(11, Calendar.DECEMBER, 2001))));

        final TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);
  //      collection.add(s2);

        return collection;
    }
    
   public static JFreeChart createChart(final IntervalCategoryDataset dataset) {
        final JFreeChart chart = ChartFactory.createGanttChart(
            "Gantt Chart Demo",  // chart title
            "Task",              // domain axis label
            "Date",              // range axis label
            dataset,             // data
            true,                // include legend
            true,                // tooltips
            false                // urls
        );    
//        chart.getCategoryPlot().getDomainAxis().setMaxCategoryLabelWidthRatio(10.0f);
        return chart;    
    }

	public boolean isViewChart() {
		return viewChart;
	}
	public void setViewChart(boolean viewChart) {
		this.viewChart = viewChart;
	}
	public int getActiveIndex() {
		return activeIndex;
	}
	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}
	public TaskEntity getSelectedTask() {
		return selectedTask;
	}
	public void setSelectedTask(TaskEntity selectedTask) {
		this.selectedTask = selectedTask;
	}
	public List<ProductEntity> getListProducts() {
		return listProducts;
	}
	public void setListProducts(List<ProductEntity> listProducts) {
		this.listProducts = listProducts;
	}
	public List<ProductEntity> getListProductWork() {
		return listProductWork;
	}
	public void setListProductWork(List<ProductEntity> listProductWork) {
		this.listProductWork = listProductWork;
	}
	public ProductEntity getProductSelected() {
		return productSelected;
	}
	public void setProductSelected(ProductEntity productSelected) {
		this.productSelected = productSelected;
	}
	public ProductEntity getProductworkSelected() {
		return productworkSelected;
	}
	public void setProductworkSelected(ProductEntity productworkSelected) {
		this.productworkSelected = productworkSelected;
	}
	
	public List<ProductEntity> getProductfilter() {
		return productfilter;
	}
	public void setProductfilter(List<ProductEntity> productfilter) {
		this.productfilter = productfilter;
	}
	

	
	
	
	public void setChart(StreamedContent chart) {
		this.chart = chart;
	}
	public String getIdImage() {
		return idImage;
	}
	public void setIdImage(String idImage) {
		this.idImage = idImage;
	}
	
	
}
