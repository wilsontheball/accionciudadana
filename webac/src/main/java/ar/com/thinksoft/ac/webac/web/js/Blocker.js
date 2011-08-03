/**************************************************************************
 * 
 * 		Blocker :: STATIC CLASS
 * 
 ***************************************************************************/

var Blocker = {
    // 'isTranslationAvailable'
    // indicates wheter the calling page has preloaded appropiate language settings.
    // If you don't understand this, ignore that parameter (or use 'false').
    initialize: function(isTranslationAvailable) {
    },
    css:{
        '-webkit-border-radius': '5px',
        '-moz-border-radius': '5px',
        'background-color': '#f8f8f8',
        color: '#273658',
        'text-align': 'center',
        height: '25px',
        'padding-top': '10px',
        width: '300px',
        'border': '3px solid #273658'
    } ,

    show: function(message) {
        var blockerSetting = {
            message: '<img src="/images/loader.gif" /> ' + message,
            css: Blocker.css
        };

        tango04.ui.blocker.blockPage(blockerSetting);
    },

    hide: function() {
        //$.unblockUI();
        tango04.ui.blocker.unBlock();
    },

    wait: function(message, DOMtoBlock, css) {
        var blockerSetting = {
            message: '<img src="/skins/default/imgs/loader.gif" /> ' + message,
            css: Blocker.css
        };

        if (DOMtoBlock != null && DOMtoBlock != undefined) {
            alert("Blocker function disabled");
        } else {
            tango04.ui.blocker.blockPage(blockerSetting);
        }
    },

    messageBox: function(message) {
        $.extend($.blockUI.defaults.overlayCSS,
            {
                backgroundColor: '#d6dfe9'
            });
        var facebox_html = '<div id="facebox" style="display:block;"> \
			    <div class="popup"> \
			      <table> \
			        <tbody> \
			          <tr> \
			            <td class="tl"/><td class="b"/><td class="tr"/> \
			          </tr> \
			          <tr> \
			            <td class="b"/> \
			            <td class="body"> \
			              <div class="facebox_content">'
								+ message +
			              '</div> \
			              <div class="footer"> \
			                <a href="#" class="close"> \
			                  <span class="button" onclick="$.unblockUI()">'
			                  		+ Blocker.translate('_lang_blocker_001') +
			                  '</span> \
			                </a> \
			              </div> \
			            </td> \
			            <td class="b"/> \
			          </tr> \
			          <tr> \
			            <td class="bl"/><td class="b"/><td class="br"/> \
			          </tr> \
			        </tbody> \
			      </table> \
			    </div> \
			  </div>';
        $.blockUI(facebox_html,
		{
		    top: '200px',
		    width: '300px'
		});
    },

    errorMessageBox: function(title, responseObject) {
        var backColor = '#d6dfe9';
        if (responseObject.exception == 'AS_ACCESS_DENIED (Access Server Error)') {
            title = Blocker.translate('_lang_blocker_006');
        }
        $.extend($.blockUI.defaults.overlayCSS,
            {
                backgroundColor: backColor
            });
        var htmlMsg = '<table id="errorMsgBox"><tbody>' +
				'<tr><th>' + title + '</th></tr>' +
				'<tr><td><div class="err_description">' + responseObject.message + '</div></td></tr>' +
				'<tr><td><div class="err_details">' + responseObject.details + '</div></td></tr>';

        if (responseObject.exception != null) {
            htmlMsg +=
				'<tr><td><div class="err_exception" style="display: none">' + responseObject.exception + '</div></td></tr>' +
				'<tr><td>' +
				'<span class="err_seeMoreBtn">' + Blocker.translate('_lang_blocker_004') + '</span>' +
				'<span class="err_seeMoreBtn" style="display: none">' + Blocker.translate('_lang_blocker_005') + '</span>' +
				'</td></tr>';
        }

        htmlMsg += '</tbody></table>';


        var facebox_html =
			'<div id="facebox" style="display:block;"> \
			    <div class="popup"> \
			      <table> \
			        <tbody> \
			          <tr> \
			            <td class="err_tl"/><td class="err_b"/><td class="err_tr"/> \
			          </tr> \
			          <tr> \
			            <td class="err_b"/> \
			            <td class="body"> \
			              <div class="facebox_content">'
								+ htmlMsg +
			              '</div> \
			              <div class="footer"> \
			                <a href="#pivot" class="close"> \
			                  <span class="button closeBtn">'
			                  		+ Blocker.translate('_lang_blocker_001') +
			                  '</span> \
			                </a> \
			              </div> \
			            </td> \
			            <td class="err_b"/> \
			          </tr> \
			          <tr> \
			            <td class="err_bl"/><td class="err_b"/><td class="err_br"/> \
			          </tr> \
			        </tbody> \
			      </table> \
			    </div> \
			  </div>';

        if ($.browser.msie) {
            $.blockUI(facebox_html,
				{
				    top: '100px',
				    width: '600px',
				    border: 'none',
				    bottom: 'auto',
				    marginLeft: '-300px'
				});
        }
        else {
            $.blockUI(facebox_html,
				{
				    top: '100px',
				    width: '600px',
				    border: 'none',
				    marginLeft: '-300px'
				});
        }

        // Assign behaviour to 'close' button
        $('#facebox span.closeBtn').click(function() {
            $.unblockUI();
        });
        if (responseObject.exception != null) {
            $('#errorMsgBox .err_seeMoreBtn').click(function() {
                $('#errorMsgBox .err_exception').toggle();
                $('#errorMsgBox .err_seeMoreBtn').toggle();
                $('#errorMsgBox .err_details').toggleClass('err_shorten');
            });
        }
    },

    question: function(message, buttons, css) {

        $.extend($.blockUI.defaults.overlayCSS,
            {
                backgroundColor: '#fff'
            });

        var facebox_html = '\
			  <div id="facebox" style="display:block;"> \
			    <div class="popup"> \
			      <table> \
			        <tbody> \
			          <tr> \
			            <td class="tl"/><td class="b"/><td class="tr"/> \
			          </tr> \
			          <tr> \
			            <td class="b"/> \
			            <td class="body"> \
			              <div class="facebox_content">'
								+ message +
			              '</div> \
			              <div class="footer"> \
			              </div> \
			            </td> \
			            <td class="b"/> \
			          </tr> \
			          <tr> \
			            <td class="bl"/><td class="b"/><td class="br"/> \
			          </tr> \
			        </tbody> \
			      </table> \
			    </div> \
			  </div>';

        $.blockUI(facebox_html, css);

        var box = $('#facebox')[0];

        $.each(buttons, function(i, n) {
            $('<a href="#" class="button" rel="' + i + '">' + i + '</a>')
				.appendTo($('.footer', box)[0])
				.click(n);
        });

        return $('.facebox_content', box)[0];
    },

    close: function(DOMtoBlock) {
        if (DOMtoBlock != null && DOMtoBlock != undefined) {
            $(DOMtoBlock).unblock();
        }
        $.unblockUI();
    },

    prompt: function(message, button_labels, button_actions) {
        if (arguments.length != 3)
            throw new Error("Wrong number of arguments");
        else if (Tango04.realTypeOf(message) != 'string' || Tango04.realTypeOf(button_labels) != 'array' || Tango04.realTypeOf(button_actions) != 'array')
            throw new Error("Wrong type for arguments. First argument should be a string, and both others array type.");
        else if (button_labels.length != button_actions.length)
            throw new Error("Different number of label/functions. Each button should have a label and an associated function");
        else {
            var buttons = '';

            for (var i = 0, top = button_labels.length; i < top; i++) {
                buttons += '<span id="bname_' + button_labels[i] + '" class="button">' + button_labels[i] + '</span>';
            }

            $.extend($.blockUI.defaults.overlayCSS,
			{
			    backgroundColor: '#d6dfe9'
			});
            var facebox_html = '\
			  <div id="facebox" style="display:block;"> \
			    <div class="popup"> \
			      <table> \
			        <tbody> \
			          <tr> \
			            <td class="tl"/><td class="b"/><td class="tr"/> \
			          </tr> \
			          <tr> \
			            <td class="b"/> \
			            <td class="body"> \
			              <div class="facebox_content">' +
						message +
						'</div> \
			              <div class="footer"> \
			                <a href="#" class="close">' +
			                  buttons +
			                '</a> \
			              </div> \
			            </td> \
			            <td class="b"/> \
			          </tr> \
			          <tr> \
			            <td class="bl"/><td class="b"/><td class="br"/> \
			          </tr> \
			        </tbody> \
			      </table> \
			    </div> \
			  </div>';

            $.blockUI(facebox_html);

            var ext_indexes = new Array();
            var aux_id;

            for (i = 0; i < top; i++) {
                aux_id = 'bname_' + button_labels[i];
                ext_indexes.push(aux_id);

                $('#' + aux_id).click(function() {
                    button_actions[ext_indexes.indexOf(this.id)]();
                    $.unblockUI();
                });
            }
        }
    },

    translate: function(translationID) {
        var out;

        if (Blocker.shouldTranslate) {
            out = $('#' + translationID).val();
        }
        else {
            switch (translationID) {
                case '_lang_blocker_001':
                    out = 'Close';
                    break;
                case '_lang_blocker_002':
                    out = 'Description';
                    break;
                case '_lang_blocker_003':
                    out = 'Details';
                    break;
                case '_lang_blocker_004':
                    out = 'See more';
                    break;
                case '_lang_blocker_005':
                    out = 'Hide';
                    break;
                case '_lang_blocker_006':
                    out = 'Insufficient Privileges';
                    break;
                default:
                    out = 'Unknown translation ID';
                    break;
            }
        }

        return out;
    },

    shouldTranslate: false
}