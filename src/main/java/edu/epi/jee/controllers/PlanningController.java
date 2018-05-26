/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.epi.jee.controllers;



import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

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
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import edu.epi.jee.dao.TaskDao;
import edu.epi.jee.dao.ProjectDao;
import edu.epi.jee.entities.TaskEntity;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class PlanningController implements Serializable {

	@EJB
	private TaskDao taskDao ;
	@EJB
	private ProjectDao projectDao ;

    public ProjectDao getProjectDao() {
        return projectDao;
    }

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }
	private List<TaskEntity>listTasks ;
	private TaskEntity taskSelected ;
	private List<TaskEntity> filtertask ;
	   private StreamedContent graphicText;
       
	    private StreamedContent chart;
	private String idImage ;
	
	public String deletetask(){
		return null ;
		
	}
        
        public List<TaskEntity> allTasks(){
        
        listTasks = taskDao.findAllTasks();
        return listTasks;
        }
	public String updatetask(){
		TaskEntity c2 = taskDao.update(taskSelected);
		listTasks = taskDao.findAllTasks();
		return null ;
	}
	public TaskDao getTaskDao() {
		return taskDao;
	}
	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

	public List<TaskEntity> getListTasks() {
		return listTasks;
	}
	public void setListTasks(List<TaskEntity> listTasks) {
		this.listTasks = listTasks;
	}
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
      /*
        int i =1;
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
public String getIdImage() {
	return idImage;
}
public void setIdImage(String idImage) {
	this.idImage = idImage;
}
public void setChart(StreamedContent chart) {
	this.chart = chart;
}

   
}



