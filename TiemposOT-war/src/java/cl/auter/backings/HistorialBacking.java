/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.backings;

import cl.auter.ejb.session.stateless.facade.TiemposSessionBeanLocal;
import cl.auter.patron.to.RegistroTiempoTO;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author drobles
 */
@ManagedBean
@ViewScoped
public class HistorialBacking extends BaseBacking implements Serializable {
    @EJB
    TiemposSessionBeanLocal ejbTiempos;
    
    List<RegistroTiempoTO> listaRegistros = new ArrayList();
    RegistroTiempoTO regSel = new RegistroTiempoTO();
    
    //Scheduler
    private ScheduleModel eventModel;
     
    private ScheduleModel lazyEventModel;
 
    private ScheduleEvent event = new DefaultScheduleEvent();
 
    private boolean showWeekends = true;
    private boolean tooltip = true;
    private boolean allDaySlot = true;
 
    private String timeFormat;
    private String slotDuration="00:30:00";
    private String slotLabelInterval;
    private String scrollTime="06:00:00";
    private String minTime="04:00:00";
    private String maxTime="20:00:00";
    private String locale="en_US";
    private String timeZone="";
    private String clientTimeZone="local";
    private String columnHeaderFormat="";
    
    @PostConstruct
    public void init(){
        listaRegistros = ejbTiempos.getUserHistoric(usurioAutenticado().getId());
        //scheduler
        eventModel = new DefaultScheduleModel();
        for(RegistroTiempoTO reg: listaRegistros)
        {
            
           // LocalDateTime startDate = LocalDateTime.parse(reg.getFecha_str(), formatter);
            DefaultScheduleEvent event1 = DefaultScheduleEvent.builder()
                    .title(reg.getNroOt() + "(" + reg.getTiempo() + " hrs.)")
                    .startDate(LocalDateTime.ofInstant(reg.getFecha().toInstant(),
                                             ZoneId.systemDefault()))
                    .endDate(LocalDateTime.ofInstant(reg.getFecha().toInstant(),
                                             ZoneId.systemDefault()).plusHours(1))
                    .description("Team A vs. Team B")
                    .build();
            eventModel.addEvent(event1);
        }
       /* event = DefaultScheduleEvent.builder()
                .title("Birthday Party")
                .startDate(today1Pm())
                .endDate(today6Pm())
                .description("Aragon")
                .overlapAllowed(true)
                .build();
        eventModel.addEvent(event);
 
        event = DefaultScheduleEvent.builder()
                .title("Breakfast at Tiffanys")
                .startDate(nextDay9Am())
                .endDate(nextDay11Am())
                .description("all you can eat")
                .overlapAllowed(true)
                .build();
        eventModel.addEvent(event);
 
        event = DefaultScheduleEvent.builder()
                .title("Plant the new garden stuff")
                .startDate(theDayAfter3Pm())
                .endDate(fourDaysLater3pm())
                .description("Trees, flowers, ...")
                .build();
        eventModel.addEvent(event);
        DefaultScheduleEvent scheduleEventAllDay=DefaultScheduleEvent.builder()
                .title("Holidays (AllDay)")
                .startDate(sevenDaysLater0am())
                .endDate(eightDaysLater0am())
                .description("sleep as long as you want")
                .allDay(true)
                .build();
        eventModel.addEvent(scheduleEventAllDay); */
    }
    
    public void eliminarReg(){
        if(regSel != null && regSel.getId() > 0)
        {
            ejbTiempos.deleteHistoric(regSel);
            listaRegistros = ejbTiempos.getUserHistoric(usurioAutenticado().getId());
            regSel = null;
        }
    }

    public List<RegistroTiempoTO> getListaRegistros() {
        return listaRegistros;
    }

    public void setListaRegistros(List<RegistroTiempoTO> listaRegistros) {
        this.listaRegistros = listaRegistros;
    }

    public RegistroTiempoTO getRegSel() {
        return regSel;
    }

    public void setRegSel(RegistroTiempoTO regSel) {
        this.regSel = regSel;
    }
    
     
 
    public ScheduleModel getEventModel() {
        return eventModel;
    }
     
    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }
 
    public ScheduleEvent getEvent() {
        return event;
    }
 
    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }
     
    public void addEvent() {
        if (event.isAllDay()) {
            //see https://github.com/primefaces/primefaces/issues/1164
            if (event.getStartDate().toLocalDate().equals(event.getEndDate().toLocalDate())) {
                event.setEndDate(event.getEndDate().plusDays(1));
            }
        }
 
        if(event.getId() == null)
            eventModel.addEvent(event);
        else
            eventModel.updateEvent(event);
         
        event = new DefaultScheduleEvent();
    }
     
    public void onEventSelect(SelectEvent<ScheduleEvent> selectEvent) {
        event = selectEvent.getObject();
    }
     
    public void onDateSelect(SelectEvent<LocalDateTime> selectEvent) {
        event = DefaultScheduleEvent.builder().startDate(selectEvent.getObject()).endDate(selectEvent.getObject().plusHours(1)).build();
    }
     
    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Delta:" + event.getDeltaAsDuration());
         
        addMessage(message);
    }
     
    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Start-Delta:" + event.getDeltaStartAsDuration() + ", End-Delta: " + event.getDeltaEndAsDuration());
         
        addMessage(message);
    }
     
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
 
    public boolean isShowWeekends() {
        return showWeekends;
    }
 
    public void setShowWeekends(boolean showWeekends) {
        this.showWeekends = showWeekends;
    }
 
    public boolean isTooltip() {
        return tooltip;
    }
 
    public void setTooltip(boolean tooltip) {
        this.tooltip = tooltip;
    }
 
    public boolean isAllDaySlot() {
        return allDaySlot;
    }
 
    public void setAllDaySlot(boolean allDaySlot) {
        this.allDaySlot = allDaySlot;
    }
 
    public String getTimeFormat() {
        return timeFormat;
    }
 
    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }
 
    public String getSlotDuration() {
        return slotDuration;
    }
 
    public void setSlotDuration(String slotDuration) {
        this.slotDuration = slotDuration;
    }
 
    public String getSlotLabelInterval() {
        return slotLabelInterval;
    }
 
    public void setSlotLabelInterval(String slotLabelInterval) {
        this.slotLabelInterval = slotLabelInterval;
    }
 
    public String getScrollTime() {
        return scrollTime;
    }
 
    public void setScrollTime(String scrollTime) {
        this.scrollTime = scrollTime;
    }
 
    public String getMinTime() {
        return minTime;
    }
 
    public void setMinTime(String minTime) {
        this.minTime = minTime;
    }
 
    public String getMaxTime() {
        return maxTime;
    }
 
    public void setMaxTime(String maxTime) {
        this.maxTime = maxTime;
    }
 
    public String getLocale() {
        return locale;
    }
 
    public void setLocale(String locale) {
        this.locale = locale;
    }
 
    public String getTimeZone() {
        return timeZone;
    }
 
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
 
    public String getClientTimeZone() {
        return clientTimeZone;
    }
 
    public void setClientTimeZone(String clientTimeZone) {
        this.clientTimeZone = clientTimeZone;
    }
 
    public String getColumnHeaderFormat() {
        return columnHeaderFormat;
    }
 
    public void setColumnHeaderFormat(String columnHeaderFormat) {
        this.columnHeaderFormat = columnHeaderFormat;
    }
    
}
