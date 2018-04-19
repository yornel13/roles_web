package servlets;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import models.Empresa;
import models.RolIndividual;
import utilidad.Const;
import utilidad.Fecha;
import utilidad.Numeros;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

@WebServlet ("/rol/rol_individual")
public class RolIndividualReport extends HttpServlet{

    private List<RolIndividual> rolIndividuals;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        rolIndividuals = (List<RolIndividual>) request.getSession().getAttribute(Const.PRINT);

        if(request.getParameter("print") != null){

            if(rolIndividuals.size() != 0) {

                String fechaRango =  "del "+ Fecha.toTextRangeNormalized(rolIndividuals.get(0).getInicio());
                Empresa empresa = (Empresa) request.getSession().getAttribute(Const.DATA_USER);

                response.setContentType("application/pdf");

                OutputStream outputStream = response.getOutputStream();
                Document document = new Document(PageSize.A4.rotate(), 30, 30, 25, 25);
                try {
                    PdfWriter.getInstance(document, outputStream);

                    document.open();

                    Integer counterLoop = 0;
                    Boolean firstPageLoop = true;
                    Boolean headerPermission = true;

                    for (RolIndividual rolIndividual :
                            rolIndividuals) {

                        if (firstPageLoop) {
                            if (counterLoop == 0) {
                                mainHeader(document);
                                descriptionFixedHeader(document, empresa.getNombre(), empresa.getNumeracion(), fechaRango);
                            }

                            tableValues(document, rolIndividual.getCedula(), rolIndividual.getDias().toString(), rolIndividual.getSalario().toString(), rolIndividual.getHorasSobreTiempo().toString(), rolIndividual.getHorasSuplementarias().toString(),
                                    rolIndividual.getTotalBonos().toString(), rolIndividual.getVacaciones().toString(), rolIndividual.getSubtotal().toString(), rolIndividual.getDecimoTercero().toString(), rolIndividual.getDecimoCuarto().toString(),
                                    rolIndividual.getReserva().toString(), rolIndividual.getJubilacionPatronal().toString(), rolIndividual.getAportePatronal().toString(), rolIndividual.getSeguros().toString(), rolIndividual.getUniformes().toString(),
                                    rolIndividual.getTotalIngreso().toString(), rolIndividual.getEmpleado(), rolIndividual.getHorasNormales().toString(), rolIndividual.getMontoHorasSobreTiempo().toString(), rolIndividual.getMontoHorasSuplementarias().toString(), "", "");

                            if (counterLoop < 4) {
                                firstPageLoop = true;
                            } else {
                                firstPageLoop = false;
                                counterLoop = 0;
                            }
                            counterLoop++;
                        } else {

                            if (headerPermission) {
                                document.newPage();
                                descriptionFixedHeader(document, empresa.getNombre(), empresa.getNumeracion(), fechaRango);
                                counterLoop = 0;
                            }
                            if (counterLoop < 6) {
                                tableValues(document, rolIndividual.getCedula(), rolIndividual.getDias().toString(), rolIndividual.getSalario().toString(), rolIndividual.getHorasSobreTiempo().toString(), rolIndividual.getHorasSuplementarias().toString(),
                                        rolIndividual.getTotalBonos().toString(), rolIndividual.getVacaciones().toString(), rolIndividual.getSubtotal().toString(), rolIndividual.getDecimoTercero().toString(), rolIndividual.getDecimoCuarto().toString(),
                                        rolIndividual.getReserva().toString(), rolIndividual.getJubilacionPatronal().toString(), rolIndividual.getAportePatronal().toString(), rolIndividual.getSeguros().toString(), rolIndividual.getUniformes().toString(),
                                        rolIndividual.getTotalIngreso().toString(), rolIndividual.getEmpleado(), rolIndividual.getHorasNormales().toString(), rolIndividual.getMontoHorasSobreTiempo().toString(), rolIndividual.getMontoHorasSuplementarias().toString(), "", "");

                                headerPermission = false;
                            } else {
                                counterLoop = 0;
                                headerPermission = true;
                            }
                            counterLoop++;
                        }

                    }

                    if (firstPageLoop) {
                        showTotal(document);
                    }

                    if (counterLoop < 5) {
                        showTotal(document);
                    } else {
                        document.newPage();
                        showTotal(document);
                    }

                    document.close();
                    outputStream.flush();

                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }else{
                PrintWriter out = response.getWriter();
                out.print("<script> alert('No hay resultados para esta fecha'); </script>");

                String typeInfo = "noDateToPDF";
                request.setAttribute("info_msg", typeInfo);
            }
        }

    }

    private void mainHeader(Document document){

        Calendar calendar = Calendar.getInstance();
        String day = Integer.toString(calendar.get(Calendar.DATE));
        String month = Integer.toString(calendar.get(Calendar.MONTH)+1) ;
        String year = Integer.toString(calendar.get(Calendar.YEAR));

        Font dateFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        Font reportTitleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.UNDERLINE);

        //FIRST FIXED HEADER
        Paragraph dateReport = new Paragraph(day+"/"+month+"/"+year, dateFont);
        dateReport.setAlignment(Element.ALIGN_RIGHT);

        Paragraph reportTitle = new Paragraph("Rol de Pago Individual.", reportTitleFont);
        reportTitle.setAlignment(Element.ALIGN_CENTER);
        reportTitle.setSpacingBefore(5);

        try {
            document.add(dateReport);
            document.add(reportTitle);
        }catch (DocumentException de){
            de.printStackTrace();
        }
    }

    private void descriptionFixedHeader(Document document, String empresa, String numeracion, String lapso){

        Font empresaFont = new Font(Font.FontFamily.HELVETICA, 12);
        Font strcFont = new Font(Font.FontFamily.HELVETICA, 11);
        Font tableFont = new Font(Font.FontFamily.HELVETICA, 10);

        //DESCRIPTION FIXED HEADER
        Chunk glue = new Chunk(new VerticalPositionMark());
        Paragraph clientFullName = new Paragraph("Empresa: "+empresa, empresaFont);
        clientFullName.add(new Chunk(glue));
        clientFullName.add(new Phrase("ST = Sobre Tiempo", strcFont));
        clientFullName.setSpacingBefore(8);
        clientFullName.setIndentationLeft(20);

        Paragraph numberRuc = new Paragraph("Numeración: "+numeracion, empresaFont);
        numberRuc.add(new Chunk(glue));
        numberRuc.add(new Phrase("RC = Recargo", strcFont));
        numberRuc.setIndentationLeft(20);

        Paragraph laps = new Paragraph("Lapso: "+lapso, empresaFont);
        laps.setIndentationLeft(20);

        //TABLE HEADER
        float[] columnWith = {2, 0.7f, 1.1f, 1.4f, 1.4f, 1.4f, 1.3f, 1.2f, 1.1f, 1.1f, 1.3f, 1.3f, 1.1f, 1.5f, 1.4f, 1.2f};
        PdfPTable table = new PdfPTable(columnWith);
        table.setSpacingBefore(10);
        table.setWidthPercentage(100);

        PdfPCell empleado = new PdfPCell(new Phrase("Empleado", tableFont));
        PdfPCell dias = new PdfPCell(new Phrase("Dias", tableFont));
        PdfPCell salario = new PdfPCell(new Phrase("Salario", tableFont));
        PdfPCell stHoras = new PdfPCell(new Phrase("ST Horas", tableFont));
        PdfPCell rcHoras = new PdfPCell(new Phrase("RC Horas", tableFont));
        PdfPCell bonos = new PdfPCell(new Phrase("Bonos", tableFont));
        PdfPCell vacacion = new PdfPCell(new Phrase("Vacacion", tableFont));
        PdfPCell subTotal = new PdfPCell(new Phrase("Subtotal", tableFont));
        PdfPCell decimoTercero = new PdfPCell(new Phrase("Decimo\nTercero", tableFont));
        PdfPCell decimoCuarto = new PdfPCell(new Phrase("Decimo\nCuarto", tableFont));
        PdfPCell fondoRecerva = new PdfPCell(new Phrase("Fondo de\n Reserva", tableFont));
        PdfPCell jubilacionPatronal = new PdfPCell(new Phrase("Jubilacion\nPatronal", tableFont));
        PdfPCell aportePatronal = new PdfPCell(new Phrase("Aporte\nPatronal", tableFont));
        PdfPCell seguros = new PdfPCell(new Phrase("Seguros\nAP/VC/RC", tableFont));
        PdfPCell uniformesGuardias = new PdfPCell(new Phrase("Uniformes\nGuardias", tableFont));
        PdfPCell totalIngresos = new PdfPCell(new Phrase("Total\nIngresos", tableFont));

        empleado.setMinimumHeight(35);
        empleado.setPaddingTop(10);
        dias.setPaddingTop(10);
        salario.setPaddingTop(10);
        stHoras.setPaddingTop(10);
        rcHoras.setPaddingTop(10);
        bonos.setPaddingTop(10);
        vacacion.setPaddingTop(10);
        subTotal.setPaddingTop(10);
        decimoTercero.setPaddingTop(5);
        decimoCuarto.setPaddingTop(5);
        fondoRecerva.setPaddingTop(5);
        jubilacionPatronal.setPaddingTop(5);
        aportePatronal.setPaddingTop(5);
        seguros.setPaddingTop(5);
        uniformesGuardias.setPaddingTop(5);
        totalIngresos.setPaddingTop(5);

        empleado.setHorizontalAlignment(Element.ALIGN_CENTER);
        dias.setHorizontalAlignment(Element.ALIGN_CENTER);
        salario.setHorizontalAlignment(Element.ALIGN_CENTER);
        stHoras.setHorizontalAlignment(Element.ALIGN_CENTER);
        rcHoras.setHorizontalAlignment(Element.ALIGN_CENTER);
        bonos.setHorizontalAlignment(Element.ALIGN_CENTER);
        vacacion.setHorizontalAlignment(Element.ALIGN_CENTER);
        subTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
        decimoTercero.setHorizontalAlignment(Element.ALIGN_CENTER);
        decimoCuarto.setHorizontalAlignment(Element.ALIGN_CENTER);
        fondoRecerva.setHorizontalAlignment(Element.ALIGN_CENTER);
        jubilacionPatronal.setHorizontalAlignment(Element.ALIGN_CENTER);
        aportePatronal.setHorizontalAlignment(Element.ALIGN_CENTER);
        seguros.setHorizontalAlignment(Element.ALIGN_CENTER);
        uniformesGuardias.setHorizontalAlignment(Element.ALIGN_CENTER);
        totalIngresos.setHorizontalAlignment(Element.ALIGN_CENTER);

        empleado.setBackgroundColor(BaseColor.CYAN);
        dias.setBackgroundColor(BaseColor.CYAN);
        salario.setBackgroundColor(BaseColor.CYAN);
        stHoras.setBackgroundColor(BaseColor.CYAN);
        rcHoras.setBackgroundColor(BaseColor.CYAN);
        bonos.setBackgroundColor(BaseColor.CYAN);
        vacacion.setBackgroundColor(BaseColor.CYAN);
        subTotal.setBackgroundColor(BaseColor.CYAN);
        decimoTercero.setBackgroundColor(BaseColor.CYAN);
        decimoCuarto.setBackgroundColor(BaseColor.CYAN);
        fondoRecerva.setBackgroundColor(BaseColor.CYAN);
        jubilacionPatronal.setBackgroundColor(BaseColor.CYAN);
        aportePatronal.setBackgroundColor(BaseColor.CYAN);
        seguros.setBackgroundColor(BaseColor.CYAN);
        uniformesGuardias.setBackgroundColor(BaseColor.CYAN);
        totalIngresos.setBackgroundColor(BaseColor.YELLOW);

        table.addCell(empleado);
        table.addCell(dias);
        table.addCell(salario);
        table.addCell(stHoras);
        table.addCell(rcHoras);
        table.addCell(bonos);
        table.addCell(vacacion);
        table.addCell(subTotal);
        table.addCell(decimoTercero);
        table.addCell(decimoCuarto);
        table.addCell(fondoRecerva);
        table.addCell(jubilacionPatronal);
        table.addCell(aportePatronal);
        table.addCell(seguros);
        table.addCell(uniformesGuardias);
        table.addCell(totalIngresos);

        try {
            document.add(clientFullName);
            document.add(numberRuc);
            document.add(laps);
            document.add(table);
        }catch (DocumentException docEx){
            docEx.printStackTrace();
        }
    }

    private void tableValues(Document document, String cedula, String dias, String salario, String stHoras, String rcHoras, String bonos,
    String vacacion, String subTotal, String decimTercero, String decimCuarto, String fondoRecerva, String jubilaPatronal, String aportPatronal,
    String seguros, String uniformes, String totalIngresos, String nombreEmpl, String salario2, String stHoras2, String rcHoras2, String ctaAhorro, String cargo) {

        Font tableFont = new Font(Font.FontFamily.HELVETICA, 10);

        //TABLE BODY
        float[] columnWith = {2, 0.7f, 1.1f, 1.4f, 1.4f, 1.4f, 1.3f, 1.2f, 1.1f, 1.1f, 1.3f, 1.3f, 1.1f, 1.5f, 1.4f, 1.2f};
        PdfPTable tableBody = new PdfPTable(columnWith);
        tableBody.setSpacingBefore(10);
        tableBody.setWidthPercentage(100);

        PdfPCell cedulaCell = new PdfPCell(new Phrase(cedula, tableFont));
        PdfPCell diasCell = new PdfPCell(new Phrase(dias, tableFont));
        PdfPCell salarioCell = new PdfPCell(new Phrase("$"+salario, tableFont));
        PdfPCell stHorasCell = new PdfPCell(new Phrase("$"+stHoras, tableFont));
        PdfPCell rcHorasCell = new PdfPCell(new Phrase("$"+rcHoras, tableFont));
        PdfPCell transporteCell = new PdfPCell(new Phrase("$"+bonos, tableFont));
        PdfPCell vacacionCell = new PdfPCell(new Phrase("$"+vacacion, tableFont));
        PdfPCell subTotalCell = new PdfPCell(new Phrase("$"+subTotal, tableFont));
        PdfPCell decimoTerceroCell = new PdfPCell(new Phrase("$"+decimTercero, tableFont));
        PdfPCell decimoCuartoCell = new PdfPCell(new Phrase("$"+decimCuarto, tableFont));
        PdfPCell fondoRecervaCell = new PdfPCell(new Phrase("$"+fondoRecerva, tableFont));
        PdfPCell jubilacionPatronalCell = new PdfPCell(new Phrase("$"+jubilaPatronal, tableFont));
        PdfPCell aportePatronalCell = new PdfPCell(new Phrase("$"+aportPatronal, tableFont));
        PdfPCell segurosCell = new PdfPCell(new Phrase("$"+seguros, tableFont));
        PdfPCell uniformesGuardiasCell = new PdfPCell(new Phrase("$"+uniformes, tableFont));
        PdfPCell totalIngresosCell = new PdfPCell(new Phrase("$"+totalIngresos, tableFont));

        PdfPCell nombreEmpleadoCell = new PdfPCell(new Phrase(nombreEmpl, tableFont));
        PdfPCell salarioSecondRow = new PdfPCell(new Phrase(salario2, tableFont));
        PdfPCell stHorasSecondRow = new PdfPCell(new Phrase(stHoras2, tableFont));
        PdfPCell rcHorasSecondRow = new PdfPCell(new Phrase(rcHoras2, tableFont));
        PdfPCell cuentaAhorroSecondRow = new PdfPCell(new Phrase(""+ctaAhorro, tableFont));

        cedulaCell.setFixedHeight(23);
        cedulaCell.setPaddingTop(5);
        diasCell.setPaddingTop(5);
        salarioCell.setPaddingTop(5);
        stHorasCell.setPaddingTop(5);
        rcHorasCell.setPaddingTop(5);
        transporteCell.setPaddingTop(5);
        vacacionCell.setPaddingTop(5);
        subTotalCell.setPaddingTop(5);
        decimoTerceroCell.setPaddingTop(5);
        decimoCuartoCell.setPaddingTop(5);
        fondoRecervaCell.setPaddingTop(5);
        jubilacionPatronalCell.setPaddingTop(5);
        aportePatronalCell.setPaddingTop(5);
        segurosCell.setPaddingTop(5);
        uniformesGuardiasCell.setPaddingTop(5);
        totalIngresosCell.setPaddingTop(5);
        salarioSecondRow.setFixedHeight(20);
        nombreEmpleadoCell.setFixedHeight(30);
        nombreEmpleadoCell.setPaddingTop(5);
        salarioSecondRow.setPaddingTop(14);
        stHorasSecondRow.setPaddingTop(14);
        rcHorasSecondRow.setPaddingTop(14);
        cuentaAhorroSecondRow.setPaddingTop(14);

        cedulaCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        diasCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        salarioCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        stHorasCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        rcHorasCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        transporteCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        vacacionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        subTotalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        decimoTerceroCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        decimoCuartoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        fondoRecervaCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        jubilacionPatronalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        aportePatronalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        segurosCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        uniformesGuardiasCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        totalIngresosCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        nombreEmpleadoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        salarioSecondRow.setHorizontalAlignment(Element.ALIGN_CENTER);
        stHorasSecondRow.setHorizontalAlignment(Element.ALIGN_CENTER);
        rcHorasSecondRow.setHorizontalAlignment(Element.ALIGN_CENTER);
        cuentaAhorroSecondRow.setHorizontalAlignment(Element.ALIGN_CENTER);

        tableBody.addCell(cedulaCell);
        tableBody.addCell(diasCell);
        tableBody.addCell(salarioCell);
        tableBody.addCell(stHorasCell);
        tableBody.addCell(rcHorasCell);
        tableBody.addCell(transporteCell);
        tableBody.addCell(vacacionCell);
        tableBody.addCell(subTotalCell);
        tableBody.addCell(decimoTerceroCell);
        tableBody.addCell(decimoCuartoCell);
        tableBody.addCell(fondoRecervaCell);
        tableBody.addCell(jubilacionPatronalCell);
        tableBody.addCell(aportePatronalCell);
        tableBody.addCell(segurosCell);
        tableBody.addCell(uniformesGuardiasCell);
        tableBody.addCell(totalIngresosCell);
        nombreEmpleadoCell.setColspan(2);
        tableBody.addCell(nombreEmpleadoCell);
        tableBody.addCell(salarioSecondRow);
        tableBody.addCell(stHorasSecondRow);
        tableBody.addCell(rcHorasSecondRow);
        cuentaAhorroSecondRow.setColspan(11);
        tableBody.addCell(cuentaAhorroSecondRow);
        tableBody.completeRow();

        try {
            document.add(tableBody);
        }catch (DocumentException docEx ) {
            docEx.printStackTrace();
        }
    }

    private void showTotal(Document document){

        Double diasTextValor = 0d;
        Double normalesTextValor = 0d;
        Double suplementariasTextValor = 0d;
        Double sobreTiempoTextValor = 0d;
        Double extraTextValor = 0d;
        Double sueldoTotalTextValor = 0d;
        Double montoSuplementariasTextValor = 0d;
        Double montoSobreTiempoTextValor = 0d;
        Double montoBonoTextValor = 0d;
        Double montoTransporteTextValor = 0d;
        Double totalBonosTextValor = 0d;
        Double subTotalTextValor = 0d;
        Double vacacionesTextValor = 0d;
        Double decimosTotalTextValor = 0d;
        Double decimoTerceroTotalTextValor = 0d;
        Double decimoCuartoTotalTextValor = 0d;
        Double montoReservaTextValor = 0d;
        Double montoJubilacionTextValor = 0d;
        Double montoAportePatronalTextValor = 0d;
        Double montoSegurosTextValor = 0d;
        Double montoUniformasTextValor = 0d;
        Double montoTotalIngresos = 0d;

        for (RolIndividual pago: rolIndividuals) {

            diasTextValor += pago.getDias();
            normalesTextValor += pago.getHorasNormales();
            suplementariasTextValor += pago.getHorasSuplementarias();
            sobreTiempoTextValor += pago.getHorasSobreTiempo();
            sueldoTotalTextValor += pago.getSalario();
            extraTextValor += (pago.getMontoHorasSuplementarias()
                    + pago.getMontoHorasSobreTiempo());
            totalBonosTextValor += pago.getTotalBonos();
            vacacionesTextValor += pago.getVacaciones();
            subTotalTextValor += pago.getSubtotal();
            decimosTotalTextValor += (pago.getDecimoCuarto()
                    + pago.getDecimoTercero());
            decimoTerceroTotalTextValor += pago.getDecimoTercero();
            decimoCuartoTotalTextValor += pago.getDecimoCuarto();
            montoReservaTextValor += pago.getReserva();
            montoSuplementariasTextValor += pago.getMontoHorasSuplementarias();
            montoSobreTiempoTextValor += pago.getMontoHorasSobreTiempo();
            montoBonoTextValor += pago.getBono();
            montoTransporteTextValor += pago.getTransporte();
            montoJubilacionTextValor += pago.getJubilacionPatronal();
            montoAportePatronalTextValor += pago.getAportePatronal();
            montoSegurosTextValor += pago.getSeguros();
            montoUniformasTextValor += pago.getUniformes();
            montoTotalIngresos += pago.getTotalIngreso();
        }


        Font totalesFont = new Font(Font.FontFamily.HELVETICA, 12);
        Font tableFont = new Font(Font.FontFamily.HELVETICA, 10);

        try {
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        Paragraph totales = new Paragraph("Totales:", totalesFont);
        totales.setSpacingBefore(15);

        //TABLE HEADER
        float[] columnWith = {1.1f, 1.4f, 1.4f, 1.4f, 1.2f, 1.3f, 1.1f, 1.1f, 1.3f, 1.3f, 1.1f, 1.3f, 1.4f, 1.4f};
        PdfPTable table = new PdfPTable(columnWith);
        table.setSpacingBefore(5);
        table.setWidthPercentage(100);

        PdfPCell salario = new PdfPCell(new Phrase("Salario", tableFont));
        PdfPCell stHoras = new PdfPCell(new Phrase("ST Horas", tableFont));
        PdfPCell rcHoras = new PdfPCell(new Phrase("RC Horas", tableFont));
        PdfPCell bonos = new PdfPCell(new Phrase("Bonos", tableFont));
        PdfPCell vacacion = new PdfPCell(new Phrase("Vacacion", tableFont));
        PdfPCell subTotal = new PdfPCell(new Phrase("Subtotal", tableFont));
        PdfPCell decimoTercero = new PdfPCell(new Phrase("Decimo\nTercero", tableFont));
        PdfPCell decimoCuarto = new PdfPCell(new Phrase("Decimo\nCuarto", tableFont));
        PdfPCell fondoRecerva = new PdfPCell(new Phrase("Fondo de\n Reserva", tableFont));
        PdfPCell jubilacionPatronal = new PdfPCell(new Phrase("Jubilacion\nPatronal", tableFont));
        PdfPCell aportePatronal = new PdfPCell(new Phrase("Aporte\nPatronal", tableFont));
        PdfPCell seguros = new PdfPCell(new Phrase("Seguros\nAP/VC/RC", tableFont));
        PdfPCell uniformesGuardias = new PdfPCell(new Phrase("Uniformes\nGuardias", tableFont));
        PdfPCell totalIngresos = new PdfPCell(new Phrase("Total\nIngresos", tableFont));

        PdfPCell salarioCell = new PdfPCell(new Phrase(Numeros.round(sueldoTotalTextValor).toString(), tableFont));
        PdfPCell stHorasCell = new PdfPCell(new Phrase(Numeros.round(montoSobreTiempoTextValor).toString(), tableFont));
        PdfPCell rcHorasCell = new PdfPCell(new Phrase(Numeros.round(montoSuplementariasTextValor).toString(), tableFont));
        PdfPCell bonosCell = new PdfPCell(new Phrase(Numeros.round(totalBonosTextValor).toString(), tableFont));
        PdfPCell vacacionCell = new PdfPCell(new Phrase(Numeros.round(vacacionesTextValor).toString(), tableFont));
        PdfPCell subTotalCell = new PdfPCell(new Phrase(Numeros.round(subTotalTextValor).toString(), tableFont));
        PdfPCell decimoTerceroCell = new PdfPCell(new Phrase(Numeros.round(decimoTerceroTotalTextValor).toString(), tableFont));
        PdfPCell decimoCuartoCell = new PdfPCell(new Phrase(Numeros.round(decimoCuartoTotalTextValor).toString(), tableFont));
        PdfPCell fondoRecervaCell = new PdfPCell(new Phrase(Numeros.round(montoReservaTextValor).toString(), tableFont));
        PdfPCell jubilacionPatronalCell = new PdfPCell(new Phrase(Numeros.round(montoJubilacionTextValor).toString(), tableFont));
        PdfPCell aportePatronalCell = new PdfPCell(new Phrase(Numeros.round(montoAportePatronalTextValor).toString(), tableFont));
        PdfPCell segurosCell = new PdfPCell(new Phrase(Numeros.round(montoSegurosTextValor).toString(), tableFont));
        PdfPCell uniformesGuardiasCell = new PdfPCell(new Phrase(Numeros.round(montoUniformasTextValor).toString(), tableFont));
        PdfPCell totalIngresosCell = new PdfPCell(new Phrase(Numeros.round(montoTotalIngresos).toString(), tableFont));

        salario.setPaddingTop(5);
        stHoras.setPaddingTop(5);
        rcHoras.setPaddingTop(5);
        bonos.setPaddingTop(5);
        vacacion.setPaddingTop(5);
        subTotal.setPaddingTop(5);
        decimoTercero.setPaddingBottom(5);
        decimoCuarto.setPaddingBottom(5);
        fondoRecerva.setPaddingBottom(5);
        jubilacionPatronal.setPaddingBottom(5);
        aportePatronal.setPaddingBottom(5);
        seguros.setPaddingBottom(5);
        uniformesGuardias.setPaddingBottom(5);
        totalIngresos.setPaddingBottom(5);

        salario.setHorizontalAlignment(Element.ALIGN_CENTER);
        stHoras.setHorizontalAlignment(Element.ALIGN_CENTER);
        rcHoras.setHorizontalAlignment(Element.ALIGN_CENTER);
        bonos.setHorizontalAlignment(Element.ALIGN_CENTER);
        vacacion.setHorizontalAlignment(Element.ALIGN_CENTER);
        subTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
        decimoTercero.setHorizontalAlignment(Element.ALIGN_CENTER);
        decimoCuarto.setHorizontalAlignment(Element.ALIGN_CENTER);
        fondoRecerva.setHorizontalAlignment(Element.ALIGN_CENTER);
        jubilacionPatronal.setHorizontalAlignment(Element.ALIGN_CENTER);
        aportePatronal.setHorizontalAlignment(Element.ALIGN_CENTER);
        seguros.setHorizontalAlignment(Element.ALIGN_CENTER);
        uniformesGuardias.setHorizontalAlignment(Element.ALIGN_CENTER);
        totalIngresos.setHorizontalAlignment(Element.ALIGN_CENTER);

        salario.setBackgroundColor(BaseColor.CYAN);
        stHoras.setBackgroundColor(BaseColor.CYAN);
        rcHoras.setBackgroundColor(BaseColor.CYAN);
        bonos.setBackgroundColor(BaseColor.CYAN);
        vacacion.setBackgroundColor(BaseColor.CYAN);
        subTotal.setBackgroundColor(BaseColor.CYAN);
        decimoTercero.setBackgroundColor(BaseColor.CYAN);
        decimoCuarto.setBackgroundColor(BaseColor.CYAN);
        fondoRecerva.setBackgroundColor(BaseColor.CYAN);
        jubilacionPatronal.setBackgroundColor(BaseColor.CYAN);
        aportePatronal.setBackgroundColor(BaseColor.CYAN);
        seguros.setBackgroundColor(BaseColor.CYAN);
        uniformesGuardias.setBackgroundColor(BaseColor.CYAN);
        totalIngresos.setBackgroundColor(BaseColor.YELLOW);

        salarioCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        stHorasCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        rcHorasCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        bonosCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        vacacionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        subTotalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        decimoTerceroCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        decimoCuartoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        fondoRecervaCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        jubilacionPatronalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        aportePatronalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        segurosCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        uniformesGuardiasCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        totalIngresosCell.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(salario);
        table.addCell(stHoras);
        table.addCell(rcHoras);
        table.addCell(bonos);
        table.addCell(vacacion);
        table.addCell(subTotal);
        table.addCell(decimoTercero);
        table.addCell(decimoCuarto);
        table.addCell(fondoRecerva);
        table.addCell(jubilacionPatronal);
        table.addCell(aportePatronal);
        table.addCell(seguros);
        table.addCell(uniformesGuardias);
        table.addCell(totalIngresos);

        table.addCell(salarioCell);
        table.addCell(stHorasCell);
        table.addCell(rcHorasCell);
        table.addCell(bonosCell);
        table.addCell(vacacionCell);
        table.addCell(subTotalCell);
        table.addCell(decimoTerceroCell);
        table.addCell(decimoCuartoCell);
        table.addCell(fondoRecervaCell);
        table.addCell(jubilacionPatronalCell);
        table.addCell(aportePatronalCell);
        table.addCell(segurosCell);
        table.addCell(uniformesGuardiasCell);
        table.addCell(totalIngresosCell);

        try {
            document.add(totales);
            document.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
