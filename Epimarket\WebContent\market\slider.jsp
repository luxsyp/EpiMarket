<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>

<f:loadBundle basename="message" var="message"/>

<x:div styleClass="sliderBack" >
    <x:div id="sliderPos">
        <x:div id="slider">
            <h:outputLink value="#" >
                <x:graphicImage value="http://media.screened.com/uploads/0/34/278312-breaking_bad_super.jpg" />
            </h:outputLink>
            <h:outputLink value="#" >
                <x:graphicImage value="http://www.power-animals.com/wp-content/uploads/breakingbad.jpg" />
            </h:outputLink>
            <h:outputLink value="#" >
                <x:graphicImage value="http://blstb.msn.com/i/56/9E115D7DC7E7EEEB60E06810C25ECB.jpg" />
            </h:outputLink>
        </x:div>
    </x:div>
</x:div>

<script type="text/javascript">
    $(document).ready(function() {
       $('#slider').coinslider({
           width: 600,
           height: 300,
           link: false,
           delay: 5000,
           sdelay: 50
       });
    });
</script>
